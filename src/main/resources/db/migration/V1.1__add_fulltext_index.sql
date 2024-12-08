ALTER TABLE artists ADD COLUMN name_search tsvector GENERATED ALWAYS AS (to_tsvector('simple', name)) STORED;

ALTER TABLE albums ADD COLUMN name_search tsvector GENERATED ALWAYS AS (to_tsvector('simple', name)) STORED;

CREATE INDEX IF NOT EXISTS artist__name_search__idx ON artists USING GIN (name_search);

CREATE INDEX IF NOT EXISTS album__name_search__idx ON albums USING GIN (name_search);

CREATE OR REPLACE VIEW artist_and_albums_view AS
SELECT 
  art.id, 
  art.name AS artist, 
  art.name_search as artist_search, 
  COALESCE(
    json_agg(
      json_build_object(
        'album', alb.name,
        'album_search', alb.name_search,
        'release', alb.release
      )
    ) FILTER (WHERE alb.id IS NOT NULL), 
    '[]'
  ) as albums,
  art.genre
FROM artists art
LEFT JOIN albums alb ON art.id = alb.artist
GROUP BY art.id;

CREATE OR REPLACE VIEW album_and_artist_view AS
SELECT 
  art.id, 
  art.name AS artist, 
  alb.name AS album,
  alb.name_search AS album_search,
  art.genre,
  alb.release
FROM artists art
LEFT JOIN albums alb ON art.id = alb.artist;