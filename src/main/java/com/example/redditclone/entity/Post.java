package com.example.redditclone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@IdClass(CompositePostPK.class)
@Table(name = "post_tbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Post {
    public Post(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue
    private Long id;

    @Id
    @ManyToOne
    @JoinColumn
    private Subreddit subreddit;

    @ManyToOne
    private User author;
    private String title;
    private String content;
    private LocalTime published;
    private LocalTime lastEdited;
}
