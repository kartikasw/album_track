package com.kartikasw.album_track.model.request;

import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.kartikasw.album_track.entity.AlbumEntity;
import com.kartikasw.album_track.util.CustomException;
import com.kartikasw.album_track.util.ErrorMessage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlbumRequest extends BaseRequest {

    private String name;

    private int release = 0;

    private Long artist;

    @Override
    public void validateForCreate(boolean create) {
        super.validateForCreate(create);

        if (getName().isEmpty()) {
            var error = String.format(ErrorMessage.ERROR_MANDATORY_FIELD, "Name");
            throw new CustomException(error, HttpStatus.BAD_REQUEST);
        }

        if (getRelease() <= 0) {
            var error = String.format(ErrorMessage.ERROR_MANDATORY_FIELD, "Release year");
            throw new CustomException(error, HttpStatus.BAD_REQUEST);
        }
    }

    public AlbumEntity toEntity() {
        var entity = new AlbumEntity();

        if (!Objects.isNull(getId())) {
            entity.setId(getId());
        }

        entity.setName(name);
        entity.setRelease(release);

        return entity;
    }

    @Override
    protected String getObjectName() {
        return "Album";
    }
}
