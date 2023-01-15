package com.example.redditclone.repository;

import com.example.redditclone.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllBySubredditIdAndPostId(long subredditId, long postId);

    Optional<Comment> findBySubredditIdAndPostIdAndId(long subredditId, long postId, long id);

    void deleteBySubredditIdAndPostIdAndId(long subredditId, long postId, long id);
}
