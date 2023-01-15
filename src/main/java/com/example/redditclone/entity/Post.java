package com.example.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POST_ID_SEQ")
    @SequenceGenerator(name = "POST_ID_SEQ", allocationSize = 1)
    private Long id;

    @JsonIgnore
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
