package com.example.redditclone.controller;

import com.example.redditclone.model.CommentModel;
import com.example.redditclone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/subreddits/{subreddit_id}/posts/{post_id}/comments")
    public List<CommentModel> getAllCommentsForPost(@PathVariable("post_id") long postId) {
        return commentService.getAllCommentsForPost(postId);
    }

    @GetMapping("/subreddits/{subreddit_id}/posts/{post_id}/comments/{id}")
    public CommentModel findComment(@PathVariable("id") long id) {
        return commentService.findCommentById(id);
    }

    @PostMapping("/subreddits/{subreddit_id}/posts/{post_id}/comments")
    public void saveComment(@RequestBody CommentModel comment,
                            @PathVariable(name = "post_id") long postId) {
        comment.setPostId(postId);
        commentService.saveComment(comment);
    }

    @DeleteMapping("/subreddits/{subreddit_id}/posts/{post_id}/comments/{id}")
    public void deleteComment(@PathVariable(name = "id") long id) {
        commentService.deleteComment(id);
    }

    @PutMapping("/subreddits/{subreddit_id}/posts/{post_id}/comments/{id}")
    public void updateComment(@RequestBody CommentModel comment,
                              @PathVariable(name = "id") long id) {
        comment.setId(id);
        commentService.updateComment(comment);
    }
}
