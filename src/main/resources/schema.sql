CREATE TABLE user_tbl (
    id BIGINT,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);
CREATE UNIQUE INDEX idx_user_tbl_name
ON user_tbl (name);

CREATE TABLE subreddit_tbl (
    id BIGINT,
    owner_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    FOREIGN KEY (owner_id) REFERENCES user_tbl(id)
);
CREATE INDEX idx_subreddit_tbl_owner
ON subreddit_tbl (owner_id);

CREATE TABLE post_tbl (
    subreddit_id BIGINT,
    id BIGINT,
    author_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(255) NOT NULL,
    published TIME NOT NULL,
    last_edited TIME NOT NULL,
    PRIMARY KEY (subreddit_id, id),
    FOREIGN KEY (subreddit_id) REFERENCES subreddit_tbl(id),
    FOREIGN KEY (author_id) REFERENCES user_tbl(id)
);
CREATE INDEX idx_post_tbl_subreddit
ON post_tbl (subreddit_id);
CREATE INDEX idx_post_tbl_author
ON post_tbl (author_id);

CREATE TABLE comment_tbl (
    subreddit_id BIGINT,
    post_id BIGINT,
    id BIGINT,
    author_id BIGINT NOT NULL,
    parent_comment_subreddit_id BIGINT,
    parent_comment_post_id BIGINT,
    parent_comment_id BIGINT,
    content VARCHAR(255) NOT NULL,
    published TIME NOT NULL,
    last_edited TIME NOT NULL,
    PRIMARY KEY (subreddit_id, post_id, id),
    FOREIGN KEY (subreddit_id) REFERENCES subreddit_tbl(id),
    FOREIGN KEY (subreddit_id, post_id) REFERENCES post_tbl(subreddit_id, id),
    FOREIGN KEY (author_id) REFERENCES user_tbl(id),
    FOREIGN KEY (parent_comment_subreddit_id, parent_comment_post_id, parent_comment_id)
        REFERENCES comment_tbl(subreddit_id, post_id, id)
);
CREATE INDEX idx_comment_tbl_author
ON comment_tbl (author_id);
CREATE INDEX idx_comment_tbl_post
ON comment_tbl (subreddit_id, post_id);
CREATE INDEX idx_comment_tbl_parent_comment
ON comment_tbl (parent_comment_subreddit_id, parent_comment_post_id, parent_comment_id);
