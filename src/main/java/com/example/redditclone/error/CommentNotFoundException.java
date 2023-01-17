package com.example.redditclone.error;

public class CommentNotFoundException extends Exception {
    public CommentNotFoundException(String message) {
        super(message);
    }
}
