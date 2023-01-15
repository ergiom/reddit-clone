package com.example.redditclone.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "user_tbl")
@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
    @SequenceGenerator(name = "USER_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name;
}
