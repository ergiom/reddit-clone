package com.example.redditclone.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
//    private List<Post> posts;
}
