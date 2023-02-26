package com.company.services;

import com.company.artist.Song;

import java.util.Scanner;

public class SongService {

    private static SongService instance = new SongService();

    private SongService() {}

    public static SongService getInstance() {

        return instance;

    }

    public static void playSong(Song song){

        song.setPlaying(true);
        System.out.println("Currently playing:\n" + song);

    }

    public static void stopSong(Song song) {

        if (song.isPlaying()) {
            song.setPlaying(false);
            System.out.println("'" + song.getName() + "' stopped.\n");
            return;
        }

        System.out.println("'" + song.getName() + "' isn't playing. Play it now? (Yes / No)");
        Scanner scan = new Scanner(System.in);

        String ans = scan.next();
        scan.nextLine();

        if(ans.equalsIgnoreCase("yes")){

            System.out.println();
            playSong(song);
            return;
        }
        System.out.println();
    }

}
