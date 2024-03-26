CREATE TABLE song (
                      id BIGSERIAL PRIMARY KEY,
                      name VARCHAR,
                      artist VARCHAR,
                      album VARCHAR,
                      length VARCHAR,
                      resource_id BIGINT,
                      year INT
);

CREATE SEQUENCE song_seq START 1;

ALTER TABLE song ALTER COLUMN id SET DEFAULT nextval('song_seq');
