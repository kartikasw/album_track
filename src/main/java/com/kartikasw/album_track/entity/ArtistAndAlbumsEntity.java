package com.kartikasw.album_track.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "artist_and_albums_view")
@Getter
@Setter
public class ArtistAndAlbumsEntity extends BaseEntity {
    @Column(name = "artist")
    private String artist;

    @Column(name = "artist_search")
    private String artistSearch;

    @Column(columnDefinition = "jsonb")
    private String albums;

    @Column(name = "genre")
    private List<String> genre;
}
