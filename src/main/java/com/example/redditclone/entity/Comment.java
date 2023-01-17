package com.example.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

//todo serialize with parent/child ids

@Entity
@IdClass(CompositeCommentPK.class)
@Table(name = "comment_tbl")
@Getter
@Setter
@ToString
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
    @ToString.Exclude
    private Post post;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_ID_SEQ")
    @SequenceGenerator(name = "COMMENT_ID_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private User author;

    @JsonIgnore
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "parent_comment_subreddit_id", referencedColumnName = "subreddit_id"),
            @JoinColumn(name = "parent_comment_post_id", referencedColumnName = "post_id"),
            @JoinColumn(name = "parent_comment_id", referencedColumnName = "id")
    })
    @ToString.Exclude
    private Comment parentComment;

    @JsonIgnore
    @OneToMany(mappedBy = "parentComment", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Comment> childComments;

    @Column(nullable = false)
    private String content;
    @Column(nullable = false, updatable = false)
    private LocalTime published;
    @Column(nullable = false)
    private LocalTime lastEdited;

    @ToString.Include
    public Long strSubredditId() {
        return post.strSubredditId();
    }

    @ToString.Include
    public Long strPostId() {
        return post.getId();
    }

    @ToString.Include
    public Long strAuthorId() {
        return author.getId();
    }

    @ToString.Include
    public Long strParentCommentId() {
        return parentComment == null ? null : parentComment.getId();
    }

    @ToString.Include
    public List<Long> strChildCommentIds() {
        return childComments == null ? null : childComments.stream().map(Comment::getId).collect(Collectors.toList());
    }
}
