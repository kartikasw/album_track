package com.kartikasw.album_track.entity.view;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;

import com.kartikasw.album_track.util.CustomException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumView {
    private String album;

    private int release;

    public static List<AlbumView> parseAlbums(String albumsJson) {
        try {
            JSONArray jsonArray = new JSONArray(albumsJson);
            List<AlbumView> albumList = new ArrayList<>();

            jsonArray.forEach(item -> {
                JSONObject albumObject = (JSONObject) item;
                AlbumView albumView = new AlbumView();
                albumView.setAlbum(albumObject.getString("album"));
                albumView.setRelease(albumObject.getInt("release"));
                albumList.add(albumView);
            });

            return albumList;
        } catch (Exception e) {
            throw new CustomException("Failed when parsing albums", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
