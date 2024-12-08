package com.kartikasw.album_track.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Formatter {
    public static String strForFullTextSearch(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return ":*";
        }

        String formattedString = Arrays.stream(keyword.trim().split("\\s+"))
                .map(word -> word + ":*")
                .collect(Collectors.joining(" & "));

        return formattedString.isEmpty() ? ":*" : formattedString;
    }
}
