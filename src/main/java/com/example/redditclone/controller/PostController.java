package com.example.redditclone.controller;

import com.example.redditclone.model.PostModel;
import com.example.redditclone.service.PostService;
import com.example.redditclone.service.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subreddits/{subreddit_id}/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private SubredditService subredditService;

    @GetMapping
    public List<PostModel> getAllSubredditPosts(@PathVariable(name = "subreddit_id") long subredditId) {
        return postService.fetchAllSubredditPosts(subredditId);
    }

    @GetMapping("{id}")
    public PostModel getPost(@PathVariable(name = "id") long id) {
        return postService.findPostById(id);
    }

    @PostMapping
    public void savePost(@RequestBody PostModel post,
                         @PathVariable(name = "subreddit_id") long subredditId) {
        post.setSubredditId(subredditId);
        postService.savePost(post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable(name = "id") long id) {
        postService.deletePostById(id);
    }

    @PutMapping("{id}")
    public void updatePost(@PathVariable(name = "id") long id,
                           @PathVariable(name = "subreddit_id") long subredditId,
                           @RequestBody PostModel newPost) {
        newPost.setId(id);
        postService.updatePost(newPost);
    }
}
