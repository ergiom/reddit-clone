package com.example.redditclone.service;

import com.example.redditclone.model.PostModel;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private List<PostModel> posts = new LinkedList<>(Arrays.asList(
            new PostModel(1L, 1L, 1L, "post nr 1", "post nr 1 content", LocalTime.now(), LocalTime.now()),
            new PostModel(2L, 1L, 1L, "post nr 2", "post nr 2 content", LocalTime.now(), LocalTime.now()),
            new PostModel(3L, 1L, 1L, "post nr 3", "post nr 3 content", LocalTime.now(), LocalTime.now()),
            new PostModel(4L, 1L, 1L, "post nr 4", "post nr 4 content", LocalTime.now(), LocalTime.now()),
            new PostModel(5L, 1L, 1L, "post nr 5", "post nr 5 content", LocalTime.now(), LocalTime.now())
    ));

    public List<PostModel> fetchAllSubredditPosts(long subredditId) {
        return posts;
    }

    public PostModel findPostById(long id) {
        Optional<PostModel> optPost = findPost(id);
        if (optPost.isPresent()) return optPost.get();

        throw new RuntimeException("Post not found");
    }

    private Optional<PostModel> findPost(long id) {
        for (PostModel post: posts) {
            if (post.getId() == id) return Optional.of(post);
        }

        return Optional.empty();
    }

    public void savePost(PostModel post) {
        LocalTime now = LocalTime.now();

        post.setPublished(now);
        post.setLastEdited(now);
        posts.add(post);
    }

    public void deletePostById(long id) {
        Optional<PostModel> optPost = findPost(id);
        if (optPost.isPresent()) {
            posts.remove(optPost.get());
            return;
        }

        throw new RuntimeException("Post not found");
    }

    public void updatePost(PostModel values) {
        Optional<PostModel> optPost = findPost(values.getId());
        if (!optPost.isPresent()) throw new RuntimeException("Post not found");

        PostModel oldPost = optPost.get();
        if (values.getContent() != null) oldPost.setContent(values.getContent());
        if (values.getTitle() != null) oldPost.setTitle(values.getTitle());

        oldPost.setLastEdited(LocalTime.now());
    }
}
