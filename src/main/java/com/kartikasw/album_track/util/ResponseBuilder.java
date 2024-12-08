package com.kartikasw.album_track.util;

import java.time.LocalDateTime;

import com.kartikasw.album_track.model.response.GeneralResponse;
import com.kartikasw.album_track.model.response.MetadataResponse;

public class ResponseBuilder {

    private ResponseBuilder() {}

    public static <T> GeneralResponse<MetadataResponse, T> builder(T response) {
        var meta = new MetadataResponse("Your request has been processed successfully", LocalDateTime.now());

        GeneralResponse<MetadataResponse, T> generalResponse = new GeneralResponse<>();
        generalResponse.setMetadata(meta);
        generalResponse.setData(response);

        return generalResponse;
    }

}