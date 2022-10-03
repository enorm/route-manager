CREATE TABLE access_tokens
(
    athlete_id    INT,
    access_token  VARCHAR(255) NOT NULL,
    refresh_token VARCHAR(255) NOT NULL,
    expires_at    TIMESTAMP,
    PRIMARY KEY (athlete_id)
);
