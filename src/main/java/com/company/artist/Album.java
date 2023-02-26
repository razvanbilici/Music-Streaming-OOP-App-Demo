package com.company.artist;

import com.company.services.AuditService;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.time.Year;


public class Album {

    private String albumId = "xxDefault";
    private String name = "noname";

    //The artist to whom it belongs
    private Artist artist;


    private Year releaseDate = Year.of(-1);

    private Genre genre = Genre.UNDEFINED;

    private static int albumNum = 0;

    public Album() throws UnknownHostException, FileNotFoundException {

        albumNum++;
        albumId = albumNum + albumId;

        artist = new Artist() {
            @Override
            public String toString() {
                return null;
            }
        };

        AuditService.writeLog("Default Song Object Created");

    }

    public Album(String name, Artist artist, Year release_date, Genre genre)
            throws UnknownHostException, FileNotFoundException {

        albumNum++;
        albumId = albumNum + "xx" + this.name + release_date;

        this.name = name;
        this.artist = artist;
        this.releaseDate = release_date;
        this.genre = genre;

        AuditService.writeLog("\"" + name + "\" Song Object Created");
    }

    public String getAlbum_id() {
        return albumId;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Year getRelease_date() {
        return releaseDate;
    }

    public void setRelease_date(Year release_date) {
        this.releaseDate = release_date;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public static int getAlbumNum() {
        return albumNum;
    }

    public String toString() {
        return "Album: " + name + "\n" +
                "By: " + artist.getName() + "\n" +
                "Genre: " + genre.toString().charAt(0) + genre.toString().substring(1).toLowerCase() + "\n" +
                "Year: " + releaseDate + "\n";
    }
}