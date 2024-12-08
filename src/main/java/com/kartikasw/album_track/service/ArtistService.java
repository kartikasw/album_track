package com.kartikasw.album_track.service;

import java.util.List;

import com.kartikasw.album_track.entity.ArtistEntity;
import com.kartikasw.album_track.entity.view.ArtistAndAlbumsView;
import com.kartikasw.album_track.model.request.ArtistParam;
import com.kartikasw.album_track.model.request.ArtistRequest;
import com.kartikasw.album_track.model.response.GeneralResponse;
import com.kartikasw.album_track.model.response.MetadataResponse;

public interface ArtistService {
    GeneralResponse<MetadataResponse, ArtistEntity> create(ArtistRequest artistRequest);

    GeneralResponse<MetadataResponse, ArtistEntity> update(ArtistRequest artistRequest);

    GeneralResponse<MetadataResponse, List<ArtistEntity>> getAll();

    GeneralResponse<MetadataResponse, List<ArtistAndAlbumsView>> getArtistAndAlbumsList(ArtistParam param);
}
