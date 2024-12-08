package com.kartikasw.album_track.entity.view;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistAndAlbumsView {
    private Long id;

    private String artist;

    private List<String> genre;

    private List<AlbumView> albums;
}
