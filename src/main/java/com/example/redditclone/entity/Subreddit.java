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
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User owner;

    private String name;
    //todo
//    private List<Post> posts;
}
