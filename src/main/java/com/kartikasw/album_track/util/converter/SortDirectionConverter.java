package com.kartikasw.album_track.util.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.kartikasw.album_track.util.enums.SortDirection;

@Component
public class SortDirectionConverter implements Converter<String, SortDirection> {

    @Override
    public SortDirection convert(String source) {
        if (source == null || source.isEmpty()) {
            throw new IllegalArgumentException("Sort direction cannot be null or empty");
        }
        return SortDirection.fromValue(source.toLowerCase());
    }
}
