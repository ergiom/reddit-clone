package com.example.redditclone.controller;

import com.example.redditclone.entity.Post;
import com.example.redditclone.error.PostNotFoundException;
import com.example.redditclone.error.SubredditNotFoundException;
import com.example.redditclone.model.PostModel;
import com.example.redditclone.service.PostService;
import com.example.redditclone.validation.SaveValidation;
import com.example.redditclone.validation.UpdateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
                        @PathVariable(name = "id") long id) throws PostNotFoundException {
        return postService.findPostById(subredditId, id);
    }

    @PostMapping
    public void savePost(@Validated(SaveValidation.class) @RequestBody PostModel post,
                         @PathVariable(name = "subreddit_id") long subredditId) throws SubredditNotFoundException {
        post.setSubredditId(subredditId);
        postService.savePost(post);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable(name = "subreddit_id") long subredditId,
                           @PathVariable(name = "id") long id) {
        postService.deletePostById(subredditId, id);
    }

    @PutMapping("{id}")
    public void updatePost(@Validated(UpdateValidation.class) @PathVariable(name = "id") long id,
                           @PathVariable(name = "subreddit_id") long subredditId,
                           @RequestBody PostModel values) throws PostNotFoundException {
        values.setId(id);
        values.setSubredditId(subredditId);
        postService.updatePost(values);
    }
}
