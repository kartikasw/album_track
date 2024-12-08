package com.kartikasw.album_track.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kartikasw.album_track.entity.AlbumAndArtistEntity;

@Repository
public interface AlbumAndArtistRepository extends JpaRepository<AlbumAndArtistEntity, Long> {
  final String FIND_BY_ALBUM = """
      SELECT
        ROW_NUMBER() OVER () AS record_id,
        aav.id,
        aav.artist,
        aav.album,
        aav.genre,
        aav.release
      FROM album_and_artist_view aav
      WHERE
        :album_name != ':*' AND album_search @@ to_tsquery('simple', :album_name);
      """;

  final String CHECK_ALBUM_IN_ARTIST = """
      SELECT EXISTS (
        SELECT 1
        FROM album_and_artist_view
        WHERE album = :album_name
      );
      """;

  @Query(value = FIND_BY_ALBUM, nativeQuery = true)
  List<AlbumAndArtistEntity> findManyByAlbum(@Param("album_name") String albumName);

  @Query(value = CHECK_ALBUM_IN_ARTIST, nativeQuery = true)
  boolean checkAlbumInArtist(@Param("album_name") String albumName);
}
