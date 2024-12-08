package com.kartikasw.album_track.service;

import java.util.List;

import com.kartikasw.album_track.entity.AlbumAndArtistEntity;
import com.kartikasw.album_track.entity.AlbumEntity;
import com.kartikasw.album_track.model.request.AlbumParam;
import com.kartikasw.album_track.model.request.AlbumRequest;
import com.kartikasw.album_track.model.response.GeneralResponse;
import com.kartikasw.album_track.model.response.MetadataResponse;

public interface AlbumService {
    GeneralResponse<MetadataResponse, AlbumEntity> create(AlbumRequest albumRequest);

    GeneralResponse<MetadataResponse, AlbumEntity> update(AlbumRequest albumRequest);

    GeneralResponse<MetadataResponse, List<AlbumEntity>> getAll(AlbumParam param);

    GeneralResponse<MetadataResponse, AlbumEntity> delete(long id);

    GeneralResponse<MetadataResponse, List<AlbumAndArtistEntity>> getAlbumAndArtistList(AlbumParam param);

}
