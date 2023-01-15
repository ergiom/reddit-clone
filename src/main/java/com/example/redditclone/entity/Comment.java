package com.example.redditclone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@Table(name = "comment_tbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(CompositeCommentPK.class)
public class Comment {

    @Id
    @ManyToOne
    @JoinColumn
    private Subreddit subreddit;

    @Id
    @ManyToOne
    @JoinColumns()
    private Post post;

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User author;

    @ManyToOne
    private Comment parentComment;

    private String content;
    private LocalTime published;
    private LocalTime lastEdited;
}
