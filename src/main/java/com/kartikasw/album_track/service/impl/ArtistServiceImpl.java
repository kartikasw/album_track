package com.kartikasw.album_track.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kartikasw.album_track.entity.ArtistAndAlbumsEntity;
import com.kartikasw.album_track.entity.ArtistEntity;
import com.kartikasw.album_track.entity.view.AlbumView;
import com.kartikasw.album_track.entity.view.ArtistAndAlbumsView;
import com.kartikasw.album_track.model.request.ArtistParam;
import com.kartikasw.album_track.model.request.ArtistRequest;
import com.kartikasw.album_track.model.response.GeneralResponse;
import com.kartikasw.album_track.model.response.MetadataResponse;
import com.kartikasw.album_track.repository.ArtistAndAlbumsRepository;
import com.kartikasw.album_track.repository.ArtistRepository;
import com.kartikasw.album_track.service.ArtistService;
import com.kartikasw.album_track.util.ResponseBuilder;

@Service
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    ArtistAndAlbumsRepository artistAndAlbumsRepository;

    @Override
    public GeneralResponse<MetadataResponse, ArtistEntity> create(ArtistRequest artistRequest) {
        artistRequest.validateForCreate(true);

        var entity = artistRequest.toEntity();
        artistRepository.save(entity);

        return ResponseBuilder.builder(entity);
    }

    @Override
    public GeneralResponse<MetadataResponse, ArtistEntity> update(ArtistRequest artistRequest) {
        artistRequest.validateForCreate(false);

        var entity = artistRequest.toEntity();
        artistRepository.save(entity);

        return ResponseBuilder.builder(entity);
    }

    @Override
    public GeneralResponse<MetadataResponse, List<ArtistEntity>> getAll() {
        return ResponseBuilder.builder(artistRepository.findAll());
    }

    @Override
    public GeneralResponse<MetadataResponse, List<ArtistAndAlbumsView>> getArtistAndAlbumsList(ArtistParam param) {
        List<ArtistAndAlbumsEntity> list;

        if (param.getArtist().isEmpty()) {
            list = artistAndAlbumsRepository.findAll();
        } else {
            list = artistAndAlbumsRepository.findManyByArtist(param.getArtist());
        }

        if (!list.isEmpty()) {
            var viewList = list.stream().map(entity -> {
                var view = new ArtistAndAlbumsView();
                view.setId(entity.getId());
                view.setArtist(entity.getArtist());
                view.setGenre(entity.getGenre());
                view.setAlbums(AlbumView.parseAlbums(entity.getAlbums()));

                return view;
            }).collect(Collectors.toList());

            return ResponseBuilder.builder(viewList);
        }

        return ResponseBuilder.builder(new ArrayList<>());
    }
}
