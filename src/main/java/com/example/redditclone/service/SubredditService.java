package com.example.redditclone.service;

import com.example.redditclone.model.SubredditModel;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class SubredditService {

    private List<SubredditModel> subreddits =
        new LinkedList<>(Arrays.asList(
                new SubredditModel(1L, 1L, "catz"),
                new SubredditModel(2L, 1L, "dogz")
        ));

    public List<SubredditModel> fetchSubreddits() {
        return subreddits;
    }

    public void saveSubreddit(SubredditModel subredditModel) {
        subreddits.add(subredditModel);
    }

    public SubredditModel fetchSubreddit(long id) {
        Optional<SubredditModel> optSubreddit = findSubreddit(id);
        if (optSubreddit.isPresent()) return optSubreddit.get();

        throw new RuntimeException("This subreddit does not exist");
    }

    public void removeSubreddit(long id) {
        Optional<SubredditModel> optSubreddit = findSubreddit(id);
        if (optSubreddit.isPresent()) {
            subreddits.remove(optSubreddit.get());
            return;
        }

        throw new RuntimeException("This subreddit does not exist");
    }

    public void updateSubreddit(long id, SubredditModel newSubreddit) {
        Optional<SubredditModel> optSubreddit = findSubreddit(id);
        if (optSubreddit.isPresent()) {
            updateSubreddit(optSubreddit.get(), newSubreddit);
            return;
        }

        throw new RuntimeException("This subreddit does not exist");
    }

    private Optional<SubredditModel> findSubredditByName(String name) {
        for (SubredditModel subreddit: subreddits) {
            if (subreddit.getName().equalsIgnoreCase(name)) return Optional.of(subreddit);
        }

        return Optional.empty();
    }

    private Optional<SubredditModel> findSubreddit(long id) {
        for (SubredditModel subreddit: subreddits) {
            if (subreddit.getId() == id) return Optional.of(subreddit);
        }

        return Optional.empty();
    }

    private void updateSubreddit(SubredditModel toUpdate, SubredditModel values) {
        if (values.getOwnerId() != null) toUpdate.setOwnerId(values.getOwnerId());
        if (values.getName() != null) toUpdate.setName(values.getName());
    }
}
