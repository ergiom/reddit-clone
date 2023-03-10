package com.example.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "subreddit_tbl")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Subreddit {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBREDDIT_ID_SEQ")
    @SequenceGenerator(name = "SUBREDDIT_ID_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private User owner;

    @JsonIgnore
    @OneToMany(mappedBy = "subreddit", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Post> posts;

    @Column(nullable = false, unique = true)
    private String name;
    //todo
//    private List<Post> posts;

    @ToString.Include
    public Long strOwnerId() {
        return owner.getId();
    }
}
