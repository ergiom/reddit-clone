package com.example.redditclone.service;

import com.example.redditclone.entity.Post;
import com.example.redditclone.entity.Subreddit;
import com.example.redditclone.model.PostModel;
import com.example.redditclone.repository.PostRepository;
import com.example.redditclone.repository.SubredditRepository;
import com.example.redditclone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SubredditRepository subredditRepository;

    public List<Post> fetchAllSubredditPosts(long subredditId) {
        return postRepository.findBySubredditId(subredditId);
    }

    public Post findPostById(long subredditId, long id) {
        Optional<Post> optPost = postRepository.findByIdAndSubredditId(id, subredditId);
        if (optPost.isPresent()) return optPost.get();

        throw new RuntimeException("Post not found");
    }

    public void savePost(PostModel postModel) {
        Optional<Subreddit> optSubreddit = subredditRepository.findById(postModel.getSubredditId());
        if (!optSubreddit.isPresent()) throw new RuntimeException("Subreddit does not exist");

        Subreddit subreddit = optSubreddit.get();
        LocalTime now = LocalTime.now();

        Post post = Post.builder()
                .subreddit(subreddit)
                .author(userRepository.findById(1L).get())
                .title(postModel.getTitle())
                .content(postModel.getContent())
                .published(now)
                .lastEdited(now)
                .build();

        log.info("Saving post: " + post);
        postRepository.save(post);
    }

    public void deletePostById(long subredditId, long id) {
        postRepository.deleteByIdAndSubredditId(id, subredditId);
    }

    public void updatePost(PostModel values) {
        Optional<Post> optPost = postRepository.findByIdAndSubredditId(values.getId(), values.getSubredditId());
        if (!optPost.isPresent()) throw new RuntimeException("Post not found");

        LocalTime now = LocalTime.now();
        Post post = optPost.get();

        if (values.getContent() != null) post.setContent(values.getContent());
        if (values.getTitle() != null) post.setTitle(values.getTitle());
        post.setLastEdited(now);

        postRepository.save(post);
    }
}
