package com.example.redditclone.controller;

import com.example.redditclone.entity.Post;
import com.example.redditclone.model.PostModel;
import com.example.redditclone.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subreddits/{subreddit_id}/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<Post> getAllSubredditPosts(@PathVariable(name = "subreddit_id") long subredditId) {
        return postService.fetchAllSubredditPosts(subredditId);
    }

    @GetMapping("{id}")
    public Post getPost(@PathVariable(name = "subreddit_id") long subredditId,
                        @PathVariable(name = "id") long id) {
        return postService.findPostById(subredditId, id);
    }

    @PostMapping
    public void savePost(@RequestBody PostModel post,
                         @PathVariable(name = "subreddit_id") long subredditId) {
        post.setSubredditId(subredditId);
        postService.savePost(post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable(name = "subreddit_id") long subredditId,
                           @PathVariable(name = "id") long id) {
        postService.deletePostById(subredditId, id);
    }

    @PutMapping("{id}")
    public void updatePost(@PathVariable(name = "id") long id,
                           @PathVariable(name = "subreddit_id") long subredditId,
                           @RequestBody PostModel values) {
        values.setId(id);
        values.setSubredditId(subredditId);
        postService.updatePost(values);
    }
}
