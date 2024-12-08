package com.kartikasw.album_track.util;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.kartikasw.album_track.model.response.GeneralErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GeneralErrorResponse> handleApiException(
            CustomException exception,
            WebRequest request) {
        HttpStatus status = exception.getHttpStatus();
        GeneralErrorResponse errorResponse = errorResponse(
                exception,
                request,
                status.value(),
                status.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<GeneralErrorResponse> handleRuntimeException(
            RuntimeException exception,
            WebRequest request) {

        GeneralErrorResponse errorResponse = errorResponse(
                exception,
                request,
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private GeneralErrorResponse errorResponse(
            RuntimeException exception,
            WebRequest webRequest,
            int httpStatus,
            String reasonPhrase) {
        return GeneralErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(httpStatus)
                .error(reasonPhrase)
                .message(exception.getMessage())
                .path(webRequest.getDescription(false))
                .build();
    }
}
