package com.company.artist;

import com.company.services.AuditService;
import com.company.user.Premium;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.ArrayList;


public class Playlist {

    private Premium user;
    private String name = "default";
    private String playlistId = "xxDefault";
    private ArrayList <Song> song = new ArrayList<>();

    private static int nrPls = 0;

    public Playlist() throws UnknownHostException, FileNotFoundException {

        nrPls++;
        user = new Premium();
        playlistId = nrPls + playlistId + user.getUsername().substring(0, 2);
        AuditService.writeLog("Default Playlist Object Created");

    }

    public Playlist(Premium user, String name) throws UnknownHostException, FileNotFoundException {

        nrPls++;

        this.user = user;
        this.name = name;

        playlistId = nrPls + name + "xx" + user.getUsername().substring(0, 2);
        AuditService.writeLog("\"" + name + "\" Playlist Object Created");


    }

    public Premium getUser() {
        return user;
    }

    public void setUser(Premium user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public ArrayList<Song> getSong() {
        return song;
    }

    public void setSong(ArrayList<Song> song) {
        this.song = song;
    }

    public static int getNrPls() {
        return nrPls;
    }

    private String showSongs(){
        if(song.size() == 0)
            return "This playlist is empty..";

        String all = "";


        for(Song i : song)
            all += (i.getName() + "\n");


        return all;
    }


    @Override
    public String toString() {
        return "Playlist: " + name + "\n" +
                "Created by: " + user.getUsername() + "\n" +
                "Tracklist:\n" + showSongs() + "\n";

    }
}