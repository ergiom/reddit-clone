package com.example.redditclone.controller;

import com.example.redditclone.model.PostModel;
import com.example.redditclone.service.PostService;
import com.example.redditclone.service.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private SubredditService subredditService;

    @GetMapping("/subreddits/{subreddit_id}/posts")
    public List<PostModel> getAllSubredditPosts(@PathVariable(name = "subreddit_id") long subredditId) {
        return postService.fetchAllSubredditPosts(subredditId);
    }

    @GetMapping("/subreddits/{subreddit_id}/posts/{id}")
    public PostModel getPost(@PathVariable(name = "id") long id) {
        return postService.findPostById(id);
    }

    @PostMapping("/subreddits/{subreddit_id}/posts")
    public void savePost(@RequestBody PostModel post,
                         @PathVariable(name = "subreddit_id") long subredditId) {
        post.setSubredditId(subredditId);
        postService.savePost(post);
    }

    @DeleteMapping("/subreddits/{subreddit_id}/posts/{id}")
    public void deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
    }

    @PutMapping("/subreddits/{subreddit_id}/posts/{id}")
    public void updatePost(@PathVariable(name = "id") long id,
                           @PathVariable(name = "subreddit_id") long subredditId,
                           @RequestBody PostModel newPost) {
        newPost.setId(id);
        newPost.setSubredditId(subredditId);
        postService.updatePost(newPost);
    }
}
