package com.example.redditclone.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "subreddit_tbl")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subreddit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBREDDIT_ID_SEQ")
    @SequenceGenerator(name = "SUBREDDIT_ID_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    private User owner;

    private String name;
    //todo
//    private List<Post> posts;
}
