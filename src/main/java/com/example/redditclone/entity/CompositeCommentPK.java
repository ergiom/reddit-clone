package com.example.redditclone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompositeCommentPK implements Serializable {

    private Long subreddit;
    private Long post;
    private Long id;
}
