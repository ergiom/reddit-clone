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
@Table(name = "post_tbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {
    @EmbeddedId
    private CompositePostPK pk;

    @ManyToOne
    private User author;
    private String title;
    private String content;
    private LocalDate published;
    private LocalDate lastEdited;
}
