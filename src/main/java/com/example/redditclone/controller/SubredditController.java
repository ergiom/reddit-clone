package com.example.redditclone.controller;

import com.example.redditclone.model.SubredditModel;
import com.example.redditclone.service.SubredditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subreddits")
public class SubredditController {

    @Autowired
    private SubredditService subredditService;

    @GetMapping
    public List<SubredditModel> getAllSubreddits() {
        return subredditService.fetchSubreddits();
    }

    @GetMapping("{id}")
    public SubredditModel getSubreddit(@PathVariable(name = "id") long id) {
        return subredditService.fetchSubreddit(id);
    }

    @PostMapping
    public void saveSubreddit(@RequestBody SubredditModel subredditModel) {
        subredditService.saveSubreddit(subredditModel);
    }

    @DeleteMapping("{id}")
    public void deleteSubreddit(@PathVariable(name = "id") long id) {
        subredditService.removeSubreddit(id);
    }

    @PutMapping("{id}")
    public void updateSubreddit(@PathVariable(name = "id") long id,
                                @RequestBody SubredditModel newSubreddit) {
        subredditService.updateSubreddit(id, newSubreddit);
    }
}
