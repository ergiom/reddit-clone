package com.example.redditclone.repository;

import com.example.redditclone.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    Optional<Post> findByIdAndSubredditId(long id, long subredditId);

    void deleteByIdAndSubredditId(long id, long subredditId);

    List<Post> findBySubredditId(long subredditId);
}
