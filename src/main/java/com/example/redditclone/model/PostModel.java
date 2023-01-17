package com.example.redditclone.model;

import com.example.redditclone.validation.SaveValidation;
import com.example.redditclone.validation.UpdateValidation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostModel {
    private Long id;
    private Long subredditId;

    @NotBlank(groups = {
            SaveValidation.class
    })
    @Pattern(regexp = "^.{3,25}$", groups = {
            SaveValidation.class,
            UpdateValidation.class
    })
    private String title;

    @NotBlank(groups = {
            SaveValidation.class
    })
    @Pattern(regexp = "^.{3,255}$", groups = {
            SaveValidation.class,
            UpdateValidation.class
    })
    private String content;
}
