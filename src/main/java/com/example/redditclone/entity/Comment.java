package com.example.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalTime;

@Entity
@IdClass(CompositeCommentPK.class)
@Table(name = "comment_tbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @JsonIgnore
    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "subreddit_id", referencedColumnName = "subreddit_id"),
            @JoinColumn(name = "post_id", referencedColumnName = "id")
    })
    private Post post;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_ID_SEQ")
    @SequenceGenerator(name = "COMMENT_ID_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    private User author;

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "parent_comment_subreddit_id", referencedColumnName = "subreddit_id"),
            @JoinColumn(name = "parent_comment_post_id", referencedColumnName = "post_id"),
            @JoinColumn(name = "parent_comment_id", referencedColumnName = "id")
    })
    private Comment parentComment;

    private String content;
    private LocalTime published;
    private LocalTime lastEdited;
}
