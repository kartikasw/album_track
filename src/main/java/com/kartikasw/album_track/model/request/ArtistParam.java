package com.kartikasw.album_track.model.request;

import org.springframework.http.HttpStatus;

import com.kartikasw.album_track.util.CustomException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistParam {
    private String artist = "";

    public void validate() {

        if (!getArtist().isEmpty() && getArtist().length() < 5) {
            var error = "Artist name must be at least 3 characters long";
            throw new CustomException(error, HttpStatus.BAD_REQUEST);
        }
    }
}
