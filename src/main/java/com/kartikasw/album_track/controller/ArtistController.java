package com.kartikasw.album_track.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kartikasw.album_track.entity.ArtistEntity;
import com.kartikasw.album_track.model.request.AlbumParam;
import com.kartikasw.album_track.model.request.ArtistParam;
import com.kartikasw.album_track.model.request.ArtistRequest;
import com.kartikasw.album_track.model.response.GeneralResponse;
import com.kartikasw.album_track.model.response.MetadataResponse;
import com.kartikasw.album_track.service.AlbumService;
import com.kartikasw.album_track.service.ArtistService;
import com.kartikasw.album_track.util.Utils;

@RestController
@RequestMapping("${api.base.path}/artist")
public class ArtistController {

    private final ArtistService artistService;

    private final AlbumService albumService;

    public ArtistController(ArtistService artistService, AlbumService albumService) {
        this.artistService = artistService;
        this.albumService = albumService;
    }

    @PostMapping(value = "")
    public GeneralResponse<MetadataResponse, ArtistEntity> create(@RequestBody ArtistRequest request) {
        return artistService.create(request);
    }

    @SuppressWarnings("rawtypes")
    @GetMapping(value = "")
    public GeneralResponse getList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String album) {

        if (name != null) {
            var param = new ArtistParam();
            param.setArtist(Utils.formatStrForFullTextSearch(name));
            param.validate();
            return artistService.getArtistAndAlbumsList(param);
        }

        if (album != null) {
            var param = new AlbumParam();
            param.setAlbum(Utils.formatStrForFullTextSearch(album));
            param.validate();
            return albumService.getAlbumAndArtistList(param);
        }

        return artistService.getAll();
    }

    @PutMapping(value = "/{id}")
    public GeneralResponse<MetadataResponse, ArtistEntity> update(
            @PathVariable("id") long id,
            @RequestBody ArtistRequest request) {
        request.setId(id);
        return artistService.update(request);
    }
}
