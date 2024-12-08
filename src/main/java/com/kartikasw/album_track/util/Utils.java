package com.kartikasw.album_track.util;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Utils {
    public static String formatStrForFullTextSearch(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return ":*";
        }

        String[] words = keyword.trim().split("\\s+");
        String formattedString = Arrays.stream(words)
                .map(word -> word + ":*")
                .collect(Collectors.joining(" & "));

        return formattedString.isEmpty() ? ":*" : formattedString;
    }
}
