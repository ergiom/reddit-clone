package com.example.redditclone.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentModel {
    private Long id;
    private Long postId;
    private Long subredditId;
    private Long parentCommentId;
    private String content;
}
