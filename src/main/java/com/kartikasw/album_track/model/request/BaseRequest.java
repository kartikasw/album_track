package com.kartikasw.album_track.model.request;

import java.util.Objects;

import org.springframework.http.HttpStatus;

import com.kartikasw.album_track.util.CustomException;
import com.kartikasw.album_track.util.ErrorMessage;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class BaseRequest {
    private Long id;

    protected abstract String getObjectName();

    public void validateForCreate(boolean create) {
        if (Objects.isNull(this)) {
            var error = String.format(ErrorMessage.ERROR_NULL_OBJECT, getObjectName());
            throw new CustomException(error, HttpStatus.NOT_FOUND);
        }

        /// The ID indicates that the user wants to update the data.
        /// Validation is not necessary for update operations.
        if (!Objects.isNull(getId()) && !create) {
            return;
        }
    }
}
