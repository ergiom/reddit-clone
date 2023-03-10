package com.example.redditclone.service;

import com.example.redditclone.entity.Comment;
import com.example.redditclone.error.CommentNotFoundException;
import com.example.redditclone.error.PostNotFoundException;
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

    public Comment findCommentById(long subredditId, long postId, long id) throws CommentNotFoundException {
        Optional<Comment> optComment = commentRepository.findByPostSubredditIdAndPostIdAndId(subredditId, postId, id);
        if (!optComment.isPresent()) throw new CommentNotFoundException("Comment of id: " + id + " does not exist");

        return optComment.get();
    }

    public void saveComment(CommentModel values) throws PostNotFoundException, CommentNotFoundException {
        log.info("Received values for new comment: " + values);
        LocalTime now = LocalTime.now();
        Comment comment = Comment.builder()
                .author(userRepository.findById(1L).get())
                .post(postService.findPostById(values.getSubredditId(), values.getPostId()))
                .content(values.getContent())
                .published(now)
                .lastEdited(now)
                .build();

        Long parentCommentId = values.getParentCommentId();
        if (parentCommentId != null) {
            Optional<Comment> optParentComment = commentRepository.findByPostSubredditIdAndPostIdAndId(
                    values.getSubredditId(),
                    values.getPostId(),
                    parentCommentId);

            if (!optParentComment.isPresent())
                throw new CommentNotFoundException("Parent comment of id: " + parentCommentId + " not found");

            Comment parentComment = optParentComment.get();
            comment.setParentComment(parentComment);
        }

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

    public void updateComment(CommentModel values) throws CommentNotFoundException {
        long commentId = values.getId();
        Optional<Comment> optComment = commentRepository.findByPostSubredditIdAndPostIdAndId(
                values.getSubredditId(),
                values.getPostId(),
                values.getId()
        );
        if (!optComment.isPresent()) throw new CommentNotFoundException("Comment of id: " + commentId + " not found");

        LocalTime now = LocalTime.now();
        Comment comment = optComment.get();

        if (values.getContent() != null) comment.setContent(values.getContent());
        comment.setLastEdited(now);
        log.info("Updating comment: " + comment);
        commentRepository.save(comment);
    }
}
