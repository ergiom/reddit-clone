package com.example.redditclone.error;

public class PostNotFoundException extends Exception {
    public PostNotFoundException(String message) {
        super(message);
    }
}
