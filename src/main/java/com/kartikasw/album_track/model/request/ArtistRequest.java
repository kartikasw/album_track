package com.kartikasw.album_track.model.request;

import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kartikasw.album_track.entity.ArtistEntity;
import com.kartikasw.album_track.util.CustomException;
import com.kartikasw.album_track.util.ErrorMessage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArtistRequest extends BaseRequest {

    private String name;

    @JsonProperty("verified_user")
    private int verifiedÍUser = 0;

    private List<String> genre;

    @Override
    public void validateForCreate(boolean create) {
        super.validateForCreate(create);

        if (getName().isEmpty()) {
            var error = String.format(ErrorMessage.ERROR_MANDATORY_FIELD, "Name");
            throw new CustomException(error, HttpStatus.BAD_REQUEST);
        }

        if (getGenre().isEmpty()) {
            var error = String.format(ErrorMessage.ERROR_MANDATORY_FIELD, "Genre");
            throw new CustomException(error, HttpStatus.BAD_REQUEST);
        }
    }

    public ArtistEntity toEntity() {
        var entity = new ArtistEntity();

        if (!Objects.isNull(getId())) {
            entity.setId(getId());
        }

        entity.setName(name);
        entity.setVerifiedUser(verifiedÍUser);
        entity.setGenre(genre);

        return entity;
    }

    @Override
    protected String getObjectName() {
        return "Artist";
    }
}
