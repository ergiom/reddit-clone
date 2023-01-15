package com.example.redditclone.service;

import com.example.redditclone.model.CommentModel;
import com.example.redditclone.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    private List<CommentModel> comments = new LinkedList<>(Arrays.asList(
            new CommentModel(1L, 1L, 1L, null, "1 comment content", LocalTime.now(), LocalTime.now()),
            new CommentModel(2L, 1L, 1L, 1L, "2 comment content", LocalTime.now(), LocalTime.now()),
            new CommentModel(3L, 1L, 1L, 1L, "3 comment content", LocalTime.now(), LocalTime.now()),
            new CommentModel(4L, 1L, 1L, 2L, "4 comment content", LocalTime.now(), LocalTime.now()),
            new CommentModel(5L, 1L, 1L, null, "5 comment content", LocalTime.now(), LocalTime.now())
    ));

    public List<CommentModel> getAllCommentsForPost(long postId) {
        return getCommentsForPost(postId);
    }

    public CommentModel findCommentById(long id) {
        Optional<CommentModel> optComment = findComment(id);
        if (!optComment.isPresent()) throw new RuntimeException("Comment not found");

        return optComment.get();
    }

    private Optional<CommentModel> findComment(long id) {
        for (CommentModel comment: comments) {
            if (comment.getId() == id) return Optional.of(comment);
        }

        return Optional.empty();
    }

    private List<CommentModel> getCommentsForPost(long postId) {
        List<CommentModel> selectedComments = new LinkedList<>();
        for (CommentModel comment: comments) {
            if (comment.getPostId() == postId) selectedComments.add(comment);
        }

        return selectedComments;
    }

    public void saveComment(CommentModel comment) {
        LocalTime now = LocalTime.now();
        comment.setPublished(now);
        comment.setLastEdited(now);
        comments.add(comment);
    }

    public void deleteComment(long id) {
        Optional<CommentModel> optComment = findComment(id);
        if (!optComment.isPresent()) throw new RuntimeException("Comment not found");

        comments.remove(optComment.get());
    }

    public void updateComment(CommentModel values) {
        Optional<CommentModel> optComment = findComment(values.getId());
        if (!optComment.isPresent()) throw new RuntimeException("Comment not found");

        LocalTime now = LocalTime.now();
        CommentModel comment = optComment.get();
        comment.setLastEdited(now);

        if (values.getContent() != null) comment.setContent(values.getContent());
    }
}
