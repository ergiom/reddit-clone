package com.example.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Comment> comments;

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

    @JsonProperty("commentIds")
    @ToString.Include
    public List<Long> strCommentIds() {
        return comments == null ? null : comments.stream().map(Comment::getId).collect(Collectors.toList());
    }
}
