CREATE TABLE IF NOT EXISTS authors (
    id       VARCHAR(60)  DEFAULT gen_random_uuid() PRIMARY KEY,
    name     VARCHAR      NOT NULL,
    created  TIMESTAMP    DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS idioms (
    id VARCHAR(255) PRIMARY KEY,
    content TEXT NOT NULL,
    created TIMESTAMP,
    updated TIMESTAMP,
    upvotes INT,
    downvotes INT,
    author_id VARCHAR(255),
    FOREIGN KEY (author_id) REFERENCES authors(id)
);

---- Insert authors
--INSERT INTO authors (id, name) VALUES
--    ('author1_id', 'Author 1'),
--    ('author2_id', 'Author 2');
--
---- Insert idioms
--INSERT INTO idioms (id, content, created, updated, upvotes, downvotes, author_id) VALUES
--    ('idiom1_id', 'Idiom 1 content', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0, 'author1_id'),
--    ('idiom2_id', 'Idiom 2 content', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0, 0, 'author2_id');
