package com.example.redditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostModel {
    private Long id;
    private Long authorId;
    private Long subredditId;
    private String title;
    private String content;
    private LocalTime published;
    private LocalTime lastEdited;
}
