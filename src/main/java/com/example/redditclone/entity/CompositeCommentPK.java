package com.example.redditclone.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
@AttributeOverrides({
        @AttributeOverride(
                name = "subreddit",
                column = @Column(name = "subreddit_id")
        ),
        @AttributeOverride(
                name = "post",
                column = @Column(name = "post_id")
        )
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompositeCommentPK implements Serializable {

    @ManyToOne
    private Subreddit subreddit;

    @ManyToOne
    private Post post;

    @GeneratedValue
    private Long id;
}
