CREATE TABLE user_tbl (
    id BIGINT,
    name VARCHAR(255),
    PRIMARY KEY (id)
);
CREATE TABLE subreddit_tbl (
    id BIGINT,
    owner_id BIGINT,
    name VARCHAR(255),
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES user_tbl(id)
);
CREATE TABLE post_tbl (
    subreddit_id BIGINT,
    id BIGINT,
    author_id BIGINT,
    title VARCHAR(255),
    content VARCHAR(255),
    published TIME,
    last_edited TIME,
    PRIMARY KEY (subreddit_id, id),
    FOREIGN KEY (subreddit_id) REFERENCES subreddit_tbl(id),
    FOREIGN KEY (author_id) REFERENCES user_tbl(id)
);
CREATE TABLE comment_tbl (
    subreddit_id BIGINT,
    post_id BIGINT,
    id BIGINT,
    author_id BIGINT,
    parent_comment_id BIGINT,
    content VARCHAR(255),
    published TIME,
    last_edited TIME,
    PRIMARY KEY (subreddit_id, post_id, id),
    FOREIGN KEY (subreddit_id) REFERENCES subreddit_tbl(id),
    FOREIGN KEY (subreddit_id, post_id) REFERENCES post_tbl(subreddit_id, id),
    FOREIGN KEY (author_id) REFERENCES user_tbl(id),
    FOREIGN KEY (subreddit_id, post_id, parent_comment_id) REFERENCES comment_tbl(subreddit_id, post_id, id)
);
