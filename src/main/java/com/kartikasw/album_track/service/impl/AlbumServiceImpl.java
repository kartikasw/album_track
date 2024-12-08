package com.kartikasw.album_track.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.kartikasw.album_track.entity.AlbumAndArtistEntity;
import com.kartikasw.album_track.entity.AlbumEntity;
import com.kartikasw.album_track.model.request.AlbumParam;
import com.kartikasw.album_track.model.request.AlbumRequest;
import com.kartikasw.album_track.model.response.GeneralResponse;
import com.kartikasw.album_track.model.response.MetadataResponse;
import com.kartikasw.album_track.repository.AlbumAndArtistRepository;
import com.kartikasw.album_track.repository.AlbumRepository;
import com.kartikasw.album_track.repository.ArtistRepository;
import com.kartikasw.album_track.service.AlbumService;
import com.kartikasw.album_track.util.CustomException;
import com.kartikasw.album_track.util.ResponseBuilder;

@Service
public class AlbumServiceImpl implements AlbumService {

    @Autowired
    ArtistRepository artistRepository;

    @Autowired
    AlbumRepository albumRepository;

    @Autowired
    AlbumAndArtistRepository albumAndArtistRepository;

    @Override
    public GeneralResponse<MetadataResponse, AlbumEntity> create(AlbumRequest albumRequest) {
        albumRequest.validateForCreate(true);
        var artist = artistRepository.findById(albumRequest.getArtist());
        if (!artist.isPresent()) {
            throw new CustomException("Artist is not found", HttpStatus.NOT_FOUND);
        }

        var albumAlreadyExist = albumAndArtistRepository.checkAlbumInArtist(albumRequest.getName());
        if (albumAlreadyExist) {
            throw new CustomException("Album already exists", HttpStatus.BAD_REQUEST);
        }

        var entity = albumRequest.toEntity();
        entity.setArtist(artist.get().getId());

        albumRepository.save(entity);

        return ResponseBuilder.builder(entity);
    }

    @Override
    public GeneralResponse<MetadataResponse, AlbumEntity> update(AlbumRequest albumRequest) {
        albumRequest.validateForCreate(false);

        var album = findAlbum(albumRequest.getId());

        if (!albumRequest.getName().isEmpty()) {
            album.setName(albumRequest.getName());
        }

        if (albumRequest.getRelease() != 0) {
            album.setRelease(album.getRelease());
        }

        albumRepository.save(album);

        return ResponseBuilder.builder(album);
    }

    @Override
    public GeneralResponse<MetadataResponse, List<AlbumEntity>> getAll(AlbumParam param) {
        if (param.getReleaseSort() != null) {
            var direction = param.getReleaseSort();
            return ResponseBuilder.builder(albumRepository.findAll(direction.sortBy("release")));
        }

        return ResponseBuilder.builder(albumRepository.findAll());
    }

    @Override
    public GeneralResponse<MetadataResponse, AlbumEntity> delete(long id) {
        var album = findAlbum(id);
        albumRepository.deleteById(id);
        return ResponseBuilder.builder(album);
    }

    @Override
    public GeneralResponse<MetadataResponse, List<AlbumAndArtistEntity>> getAlbumAndArtistList(AlbumParam param) {
        List<AlbumAndArtistEntity> list;

        if (param.getAlbum().isEmpty()) {
            list = albumAndArtistRepository.findAll();
        } else {
            list = albumAndArtistRepository.findManyByAlbum(param.getAlbum());
        }

        return ResponseBuilder.builder(list);
    }

    private AlbumEntity findAlbum(long id) {
        var album = albumRepository.findById(id);

        if (!album.isPresent()) {
            throw new CustomException("Album is not found", HttpStatus.NOT_FOUND);
        }

        return album.get();
    }

}
