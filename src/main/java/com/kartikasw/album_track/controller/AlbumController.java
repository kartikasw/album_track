package com.kartikasw.album_track.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kartikasw.album_track.entity.AlbumEntity;
import com.kartikasw.album_track.model.request.AlbumParam;
import com.kartikasw.album_track.model.request.AlbumRequest;
import com.kartikasw.album_track.model.response.GeneralResponse;
import com.kartikasw.album_track.model.response.MetadataResponse;
import com.kartikasw.album_track.service.AlbumService;
import com.kartikasw.album_track.util.Utils;
import com.kartikasw.album_track.util.enums.SortDirection;

@RestController
@RequestMapping("${api.base.path}/album")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping(value = "")
    public GeneralResponse<MetadataResponse, AlbumEntity> create(@RequestBody AlbumRequest request) {
        return albumService.create(request);
    }

    @SuppressWarnings("rawtypes")
    @GetMapping(value = "")
    public GeneralResponse getList(
            @RequestParam(value = "name", required = false) String album,
            @RequestParam(value = "release_sort", required = false) SortDirection releaseSort) {
        var param = new AlbumParam();
        if (releaseSort != null) {
            param.setReleaseSort(releaseSort);
        }

        if (album != null) {
            param.setAlbum(Utils.formatStrForFullTextSearch(album));
            param.validate();
            return albumService.getAlbumAndArtistList(param);
        }

        return albumService.getAll(param);
    }

    @PutMapping(value = "/{id}")
    public GeneralResponse<MetadataResponse, AlbumEntity> updtae(
            @PathVariable("id") long id,
            @RequestBody AlbumRequest request) {
        request.setId(id);
        return albumService.update(request);
    }

    @DeleteMapping(value = "/{id}")
    public GeneralResponse<MetadataResponse, AlbumEntity> delete(@PathVariable("id") long id) {
        return albumService.delete(id);
    }
}
