package com.kartikasw.album_track.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.kartikasw.album_track.entity.ArtistAndAlbumsEntity;

@Repository
public interface ArtistAndAlbumsRepository extends JpaRepository<ArtistAndAlbumsEntity, Long> {
  final String FIND_BY_ARTIST = """
      SELECT
        aav.id,
        aav.artist,
        aav.artist_search,
        aav.albums,
        aav.genre
      FROM artist_and_albums_view aav
      WHERE
        :artist_name != ':*' AND artist_search @@ to_tsquery('simple', :artist_name);
      """;

  @Query(value = FIND_BY_ARTIST, nativeQuery = true)
  List<ArtistAndAlbumsEntity> findManyByArtist(@Param("artist_name") String artistName);
}
