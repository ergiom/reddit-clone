package com.example.redditclone.controller;

import com.example.redditclone.entity.Subreddit;
import com.example.redditclone.error.SubredditNotFoundException;
import com.example.redditclone.error.UserNotFoundException;
import com.example.redditclone.model.SubredditModel;
import com.example.redditclone.service.SubredditService;
import com.example.redditclone.validation.SaveValidation;
import com.example.redditclone.validation.UpdateValidation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subreddits")
public class SubredditController {

    @Autowired
    private SubredditService subredditService;

    @GetMapping
    public List<Subreddit> getAllSubreddits() {
        return subredditService.fetchSubreddits();
    }

    @GetMapping("{id}")
    public Subreddit getSubreddit(@PathVariable(name = "id") long id) throws SubredditNotFoundException {
        return subredditService.fetchSubreddit(id);
    }

    @PostMapping
    public void saveSubreddit(@Validated(SaveValidation.class) @RequestBody SubredditModel subredditModel) {
        subredditService.saveSubreddit(subredditModel);
    }

    @DeleteMapping("{id}")
    public void deleteSubreddit(@PathVariable(name = "id") long id) {
        subredditService.removeSubreddit(id);
    }

    @PutMapping("{id}")
    public void updateSubreddit(@Validated(UpdateValidation.class) @PathVariable(name = "id") long id,
                                @RequestBody SubredditModel newSubreddit) throws SubredditNotFoundException, UserNotFoundException {
        subredditService.updateSubreddit(id, newSubreddit);
    }
}
