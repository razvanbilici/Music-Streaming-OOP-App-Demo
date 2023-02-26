package com.company.services;

import com.company.artist.Playlist;
import com.company.artist.Song;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Collectors;


public class PlaylistService {

    private static PlaylistService instance = new PlaylistService();

    private PlaylistService() {}

    public static PlaylistService getInstance() {

        return instance;

    }

    //Adding a song multiple times is allowed
    public static void Add(Song song, Playlist playlist) throws UnknownHostException, FileNotFoundException {

        AuditService.writeLog("Song \"" + song.getName() + " Successfully Added to Playlist: "
                + playlist.getName());

        playlist.getSong().add(song);

    }

    public static void Remove(Song song, Playlist playlist) throws UnknownHostException, FileNotFoundException {

        if(!(playlist.getSong().contains(song))){

            AuditService.writeLog("Song Removal Error: " + song.getName() + " Not In " + playlist.getName());

            System.out.println(song.getName() + ": Song couldn't be removed from playlist - Not Found\n");
            return;

        }

        AuditService.writeLog("Song \"" + song.getName() + " Successfully Removed From Playlist: "
                + playlist.getName());

        playlist.getSong().remove(song);

    }


    //Sort playlist by song length
    public static void MinSortUp(Playlist playlist) throws UnknownHostException, FileNotFoundException {

        System.out.println("Order By: Length Ascending - Active\n");

        AuditService.writeLog("Playlist \"" + playlist.getName() + "\" Sorted by: Length Ascending");


        //Replaced old bubblesort methods with streams
        playlist.setSong(playlist.getSong()
                        .stream()
                        .sorted(Comparator
                        .comparing(Song::getMin))
                        .collect(Collectors.toCollection(ArrayList::new)));

    }

    public static void MinSortDown(Playlist playlist) throws UnknownHostException, FileNotFoundException {

        System.out.println("Order By: Length Descending - Active\n");

        AuditService.writeLog("Playlist \"" + playlist.getName() + "\" Sorted by: Length Descending");


        //Replaced old bubblesort methods with streams
        playlist.setSong(playlist.getSong()
                .stream()
                .sorted(Comparator
                        .comparing(Song::getMin).reversed())
                .collect(Collectors.toCollection(ArrayList::new)));

    }

}