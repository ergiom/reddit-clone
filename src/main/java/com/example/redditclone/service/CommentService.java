package com.example.redditclone.service;

import com.example.redditclone.entity.Comment;
import com.example.redditclone.model.CommentModel;
import com.example.redditclone.repository.CommentRepository;
import com.example.redditclone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    //todo replace with service
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostService postService;

    public List<Comment> getAllCommentsForPost(long subredditId, long postId) {
        return commentRepository.findAllByPostSubredditIdAndPostId(subredditId, postId);
    }

    public Comment findCommentById(long subredditId, long postId, long id) {
        Optional<Comment> optComment = commentRepository.findByPostSubredditIdAndPostIdAndId(subredditId, postId, id);
        if (!optComment.isPresent()) throw new RuntimeException(
                "Comment of id: " + id +
                " to post of id: " + postId +
                " in subreddit of id: " + subredditId +
                " not found"
        );

        return optComment.get();
    }

    public void saveComment(CommentModel values) {
        LocalTime now = LocalTime.now();
        Optional<Comment> optParentComment = commentRepository.findByPostSubredditIdAndPostIdAndId(
                values.getSubredditId(),
                values.getPostId(),
                values.getParentCommentId());

        Comment comment = Comment.builder()
                .author(userRepository.findById(1L).get())
                .parentComment(optParentComment.orElse(null))
                .post(postService.findPostById(values.getSubredditId(), values.getPostId()))
                .content(values.getContent())
                .published(now)
                .lastEdited(now)
                .build();

        log.info("Saving new comment: " + comment);
        commentRepository.save(comment);
    }

    public void deleteComment(long subredditId, long postId, long id) {
        Optional<Comment> optComment = commentRepository.findByPostSubredditIdAndPostIdAndId(subredditId, postId, id);
        if (!optComment.isPresent()) return;
        Comment comment = optComment.get();

        log.info("Deleting comment: " + comment);
        commentRepository.delete(comment);
    }

    public void updateComment(CommentModel values) {
        Optional<Comment> optComment = commentRepository.findByPostSubredditIdAndPostIdAndId(
                values.getSubredditId(),
                values.getPostId(),
                values.getId()
        );
        if (!optComment.isPresent()) throw new RuntimeException("Comment not found");

        LocalTime now = LocalTime.now();
        Comment comment = optComment.get();

        if (values.getContent() != null) comment.setContent(values.getContent());
        comment.setLastEdited(now);
        log.info("Updating comment: " + comment);
        commentRepository.save(comment);
    }
}
