CREATE TABLE resource (
                          id BIGSERIAL PRIMARY KEY,
                          mp3File BYTEA
);

CREATE SEQUENCE resource_seq START 1;

ALTER TABLE resource ALTER COLUMN id SET DEFAULT nextval('resource_seq');
