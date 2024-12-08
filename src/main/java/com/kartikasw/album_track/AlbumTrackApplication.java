package com.kartikasw.album_track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AlbumTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlbumTrackApplication.class, args);
	}

}

/// TEST 1: SQL Fundamental
/// 1. Display all data (artist and album)
/// - For displaying all artists: [GET] /artist
/// - For displaying all albums: [GET] /album
/// 
/// 2. Display the album name with the old release
/// [GET] /album?release_sort=asc
/// 
/// Write a query that produces data
/// - For getting artist only: [GET] /artist
/// - For getting artist with albums: 
/// 	* [GET] /artist?name={artist_name}
/// 	* [GET] /artist?album={album_name}
/// 
/// 
/// 
/// TEST 2: RESTful APIs
/// 1. Create a Artist. Allow users to add a new artist with attributes like name, verified user, and genre
/// [POST] /artist
/// 2. Read Artist. Allow users to retrieve a artist based on specific criteria.
/// [GET] /artist
/// 3. Create a Album. Allow users to add a new album with attributes like name, release date, and 
/// choose the artist according to the albums they have!
/// [POST] /album
/// 4. Update Album. Allow users to update an album that was created previously.
/// [PUT] /album/{id}
///	5. Delete Album. Allow users to delete an album
/// [DELETE] /album
