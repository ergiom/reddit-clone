package com.example.redditclone.controller;

import com.example.redditclone.entity.Comment;
import com.example.redditclone.model.CommentModel;
import com.example.redditclone.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subreddits/{subreddit_id}/posts/{post_id}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> getAllCommentsForPost(@PathVariable("subreddit_id") long subredditId,
                                               @PathVariable("post_id") long postId) {
        return commentService.getAllCommentsForPost(subredditId, postId);
    }

    @GetMapping("{id}")
    public Comment findComment(@PathVariable("subreddit_id") long subredditId,
                               @PathVariable("post_id") long postId,
                               @PathVariable("id") long id) {
        return commentService.findCommentById(subredditId, postId, id);
    }

    @PostMapping
    public void saveComment(@RequestBody CommentModel comment,
                            @PathVariable("subreddit_id") long subredditId,
                            @PathVariable("post_id") long postId) {
        comment.setSubredditId(subredditId);
        comment.setPostId(postId);
        commentService.saveComment(comment);
        comment.setParentCommentId(null);
    }

    @PostMapping("{parent_id}")
    public void saveChildComment(@RequestBody CommentModel comment,
                                 @PathVariable("parent_id") long parentId,
                                 @PathVariable("subreddit_id") long subredditId,
                                 @PathVariable("post_id") long postId) {
        comment.setSubredditId(subredditId);
        comment.setPostId(postId);
        comment.setParentCommentId(parentId);
        commentService.saveComment(comment);
    }

    @DeleteMapping("{id}")
    public void deleteComment(@PathVariable("subreddit_id") long subredditId,
                              @PathVariable("post_id") long postId,
                              @PathVariable("id") long id) {
        commentService.deleteComment(subredditId, postId, id);
    }

    @PutMapping("{id}")
    public void updateComment(@RequestBody CommentModel comment,
                              @PathVariable("subreddit_id") long subredditId,
                              @PathVariable("post_id") long postId,
                              @PathVariable("id") long id) {
        comment.setSubredditId(subredditId);
        comment.setPostId(postId);
        comment.setId(id);
        commentService.updateComment(comment);
    }
}
