package com.example.redditclone.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subreddit {
    @Id
    private Long id;

    @ManyToOne
    private User owner;

    private String name;
    private List<Post> posts;
}
