package com.kartikasw.album_track.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "artists")
@Getter
@Setter
public class ArtistEntity extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "verified_user")
    private int verifiedUser;

    @Column(name = "genre")
    private List<String> genre;
}