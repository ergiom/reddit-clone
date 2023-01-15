package com.example.redditclone.service;

import com.example.redditclone.entity.Subreddit;
import com.example.redditclone.entity.User;
import com.example.redditclone.model.SubredditModel;
import com.example.redditclone.repository.SubredditRepository;
import com.example.redditclone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubredditService {

    @Autowired
    private SubredditRepository subredditRepository;
    @Autowired
    private UserRepository userRepository;


    public List<Subreddit> fetchSubreddits() {
        return subredditRepository.findAll();
    }

    public void saveSubreddit(SubredditModel subredditModel) {
        User user = userRepository.findById(1L).get();
        Subreddit subreddit = Subreddit.builder()
                .owner(user)
                .name(subredditModel.getName())
                .build();

        subredditRepository.save(subreddit);
    }

    public Subreddit fetchSubreddit(long id) {
        Optional<Subreddit> optSubreddit = subredditRepository.findById(id);
        if (optSubreddit.isPresent()) return optSubreddit.get();

        throw new RuntimeException("This subreddit does not exist");
    }

    public void removeSubreddit(long id) {
        subredditRepository.deleteById(id);
    }

    public void updateSubreddit(long id, SubredditModel values) {
        Optional<Subreddit> optSubreddit = subredditRepository.findById(id);
        if (!optSubreddit.isPresent()) throw new RuntimeException("This subreddit does not exist");

        Subreddit subreddit = optSubreddit.get();
        updateSubreddit(subreddit, values);
        subredditRepository.save(subreddit);
    }

    private void updateSubreddit(Subreddit subreddit, SubredditModel values) {
        if (values.getOwnerId() != null) {
            Optional<User> optOwner = userRepository.findById(values.getOwnerId());
            if (!optOwner.isPresent()) throw new RuntimeException("No user of this id");

            User owner = optOwner.get();
            subreddit.setOwner(owner);
        }
        if (values.getName() != null) subreddit.setName(values.getName());
    }
}
