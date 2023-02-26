package com.company;

import com.company.artist.*;
import com.company.exceptions.ReadFromFileException;
import com.company.exceptions.WriteToFileException;
import com.company.label.*;
import com.company.repos.*;
import com.company.services.*;
import com.company.user.*;
import com.company.dbconfig.DbConfig;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.Year;
import java.util.ArrayList;
import java.util.Arrays;


//Bilici Mihai - Razvan
//CTI - 262

//Implemented csv file system for bands only
public class Main {

    public static void main(String[] args) throws FileNotFoundException, ReadFromFileException, UnknownHostException {



        AuditService.writeLog("Main Run");

        IndieLabel JIVE = new IndieLabel("Jive", Year.of(2001), "John Doe");
        IndieLabel BLACKENED = new IndieLabel("Blackened", Year.of(2012), "Metallica CO");

        Band BOSQUITO = new Band("Bosquito", 6,  Genre.ROCK, Year.of(2001), JIVE, Status.ACTIVE);
        Band METALLICA = new Band("Metallica", 4,  Genre.METAL, Year.of(1983), BLACKENED, Status.ACTIVE);

        Album JUSTICE = new Album("..And Justice For All", METALLICA, Year.of(1988), Genre.METAL);
        Album BABYLON = new Album("Babylon", BOSQUITO, Year.of(2014), Genre.FOLK);

        Song TOBOGAN = new Song("Tobogan", BABYLON, Year.of(2014), 4);
        Song ONE = new Song("One", JUSTICE, Year.of(1988), 8);

        User eu = new User("razvanBlc01", "rzb@yahoo.com", "johndoe05");
        Premium RONALDO = new Premium("CRonaldo7", "CR7@yahoo.com", "cr7manu08", Genre.FOLK);




        //drop table x cascade merge doar in postgre aparent
//        LabelRepo.dropTable();
//        BandRepo.dropTable();
//        AlbumRepo.dropTable();
//        SongRepo.dropTable();
//        UserRepo.dropTable();
//        FollowsRepo.dropTable();

        BOSQUITO.getArtist_id();


        LabelRepo.createTable();
        LabelRepo.insertLabel(JIVE);
        LabelRepo.insertLabel(BLACKENED);


        BandRepo.createTable();
        BandRepo.insertBand(BOSQUITO);
        BandRepo.insertBand(METALLICA);

        AlbumRepo.createTable();
        AlbumRepo.insertAlbum(JUSTICE);
        AlbumRepo.insertAlbum(BABYLON);

        SongRepo.createTable();
        SongRepo.insertSong(ONE);
        SongRepo.insertSong(TOBOGAN);

        UserRepo.createTable();
        UserRepo.insertUser(eu);

        FollowsRepo.createTable();

        UserService.Follow(eu, METALLICA);
        UserService.Follow(eu, BOSQUITO);
        UserService.Unfollow(eu, BOSQUITO);


//        SongService.playSong(ONE);
//        SongService.stopSong(ONE);
//        SongService.stopSong(ONE);
//
//
//        Playlist DIVERSE = new Playlist(RONALDO, "Diverse");
//
//
//        PlaylistService.Add(ONE, DIVERSE);
//        PlaylistService.Add(TOBOGAN, DIVERSE);
//
//        System.out.println(RONALDO);
//
//
//        System.out.println("(main printed) Diverse nesortat:\n" + DIVERSE);
//        System.out.println("----------------\n");
//
//
//        PlaylistService.MinSortUp(DIVERSE);
//        System.out.println(DIVERSE);
//
//
//        PlaylistService.MinSortDown(DIVERSE);
//        System.out.println(DIVERSE);



        AuditService.writeLog("Main Exited Successfully\n----------------------------------\n\n\n");

    }
}