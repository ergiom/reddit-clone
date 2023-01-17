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
@AllArgsConstructor
@NoArgsConstructor
public class SubredditModel {
    private Long ownerId;

    @NotBlank(groups = {
            SaveValidation.class
    })
    @Pattern(regexp = "^\\w{3,25}$", groups = {
            SaveValidation.class,
            UpdateValidation.class
    })
    private String name;
}
