package com.example.redditclone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "comment_tbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @EmbeddedId
    private CompositeCommentPK pk;

    @ManyToOne
    private User author;

    @ManyToOne
    private Comment parentComment;

    private String content;
    private LocalDate published;
    private LocalDate lastEdited;
}
