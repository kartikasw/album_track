package com.kartikasw.album_track.util.enums;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

public enum SortDirection {
    ASC("ASC"),
    DESC("DESC");

    private final String direction;

    SortDirection(String direction) {
        this.direction = direction;
    }

    public String getDirection() {
        return direction;
    }

    public Sort sortBy(String properties) {
        Order orderSort = this == SortDirection.ASC ? Order.asc(properties) : Order.desc(properties);
        return Sort.by(orderSort);
    }

    public static SortDirection fromValue(String value) {
        return SortDirection.valueOf(value.toUpperCase());
    }

}
