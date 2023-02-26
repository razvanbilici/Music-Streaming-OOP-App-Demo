package com.company.artist;

import com.company.services.AuditService;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.time.Year;
import java.util.Scanner;

public class Song {

    private String songId = "xxDefault";
    private String name = "noname";

    //The artist and album to whom it belongs
    private Artist artist;
    private Album album;

    //A song can be released as a single prior to the release of the full album, hence the different years
    private Year releaseDate = Year.of(-1);

    private int min = -1;

    private static int songNum = 0;

    private boolean isPlaying = false;

    public Song() throws UnknownHostException, FileNotFoundException {

        songNum++;
        songId = songNum + songId;

        artist = new Artist() {
            @Override
            public String toString() {
                return null;
            }
        };
        album = new Album();
        AuditService.writeLog("Default Song Object Created");

    }

    public Song(String name, Album album, Year release_date, int min)
            throws UnknownHostException, FileNotFoundException {

        songNum++;
        songId = songNum + "xx" + this.name + release_date;

        this.name = name;
        this.album = album;
        this.releaseDate = release_date;
        this.min = min;

        this.artist = album.getArtist();

        AuditService.writeLog("\"" + name + "\" Song Object Created");
    }


    public String getSong_id() {
        return songId;
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

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public Year getRelease_date() {
        return releaseDate;
    }

    public void setRelease_date(Year release_date) {
        this.releaseDate = release_date;
    }

    public static int getSongNum() {
        return songNum;
    }

    public boolean isPlaying() {
        return isPlaying;
    }

    public void setPlaying(boolean playing) {
        isPlaying = playing;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String toString() {
        return "Song: " + name + "\n" +
                "By: " + artist.getName() + "\n" +
                "Album: " + album.getName() + "\n" +
                "Year: " + releaseDate + "\n"+
                "Length: " + min + " minutes\n";

    }


}