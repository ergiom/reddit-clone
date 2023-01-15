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
        )
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CompositePostPK implements Serializable {

    @ManyToOne
    private Subreddit subreddit;

    @GeneratedValue
    private Long id;
}
