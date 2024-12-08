package com.kartikasw.album_track.model.request;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kartikasw.album_track.util.CustomException;
import com.kartikasw.album_track.util.enums.SortDirection;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumParam {
    private String album = "";

    @JsonProperty("release_sort")
    private SortDirection releaseSort;

    public void validate() {
        if (!getAlbum().isEmpty() && getAlbum().length() < 5) {
            var error = "Album name must be at least 3 characters long";
            throw new CustomException(error, HttpStatus.BAD_REQUEST);
        }
    }
}
