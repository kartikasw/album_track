package com.kartikasw.album_track.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "albums")
@Getter
@Setter
public class AlbumEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "release")
    private int release;

    @Column(name = "artist")
    private Long artist;
}
