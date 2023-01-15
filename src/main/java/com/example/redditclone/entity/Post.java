package com.example.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@IdClass(CompositePostPK.class)
@Table(name = "post_tbl")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_ID_SEQ")
    @SequenceGenerator(name = "POST_ID_SEQ", allocationSize = 1)
    private Long id;

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Subreddit subreddit;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private User author;

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false, updatable = false)
    private LocalTime published;
    @Column(nullable = false)
    private LocalTime lastEdited;

    @ToString.Include
    public Long strSubredditId() {
        return subreddit.getId();
    }

    @ToString.Include
    public Long strAuthorId() {
        return author.getId();
    }
}
