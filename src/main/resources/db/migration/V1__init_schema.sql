CREATE TABLE IF NOT EXISTS artists (
  id bigserial NOT NULL,
  name varchar(50) NOT NULL,
  verified_user smallint NOT NULL DEFAULT 0,
  genre text[] NOT NULL,
  created_at timestamptz NOT NULL DEFAULT (now()),
  updated_at timestamptz NOT NULL DEFAULT (now()),

  CONSTRAINT artist__pkey PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS albums (
  id bigserial NOT NULL,
  name varchar(50) NOT NULL,
  release int NOT NULL,
  artist bigserial,
  created_at timestamptz NOT NULL DEFAULT (now()),
  updated_at timestamptz NOT NULL DEFAULT (now()),

  CONSTRAINT album__pkey PRIMARY KEY (id),
  CONSTRAINT artist_id__fk FOREIGN KEY (artist) REFERENCES artists(id) ON DELETE CASCADE
);

CREATE INDEX IF NOT EXISTS artist__id__idx ON artists USING BTREE (id);

CREATE INDEX IF NOT EXISTS artist__genre__idx ON artists USING BTREE (id);

CREATE INDEX IF NOT EXISTS album__artist__idx ON albums USING BTREE (artist);

CREATE OR REPLACE FUNCTION update_updated_at_column()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER update_artists_updated_at
BEFORE UPDATE ON artists
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TRIGGER update_albums_updated_at
BEFORE UPDATE ON albums
FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();