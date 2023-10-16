CREATE TABLE IF NOT EXISTS user_favorite_movies (
    user_id BIGINT,
    movie_id BIGINT,
    PRIMARY KEY (user_id, movie_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (movie_id) REFERENCES movies (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS user_favorite_movies
  OWNER TO postgres;

CREATE INDEX idx_user_favorite_movies_user_id ON user_favorite_movies (user_id);
CREATE INDEX idx_user_favorite_movies_movie_id ON user_favorite_movies (movie_id);