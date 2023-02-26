package com.company.artist;

import com.company.label.*;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.time.Year;



public abstract class Artist {

    private String artistId = "xxDefault";
    private String name = "noname";

    //Defying genre != album / song genre
    private Genre genre = Genre.UNDEFINED;
    private Year debut = Year.of(-1);

    private Label label;

    // Active / Retired
    Status status = Status.UNDEFINED;

    private static int artistNum = 0;


    //Constructors
    protected Artist() throws UnknownHostException, FileNotFoundException {

        artistNum++;
        artistId = artistNum + artistId;
        label = new Label();

    }

    protected Artist(String name, Genre genre, Year debut, Label label, Status status){

        artistNum++;

        //e.g. Scorpions - 5SC9_1970
        artistId = artistNum + name.substring(0, 2).toUpperCase() + name.length() + "xx" + debut;

        this.name = name;
        this.genre = genre;
        this.debut = debut;
        this.label = label;
        this.status = status;

    }

    public String getArtist_id() {
        return artistId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Year getDebut() {
        return debut;
    }

    public void setDebut(Year debut) {
        this.debut = debut;
    }

    public Label getLabel() {
        return label;
    }

    public void setLabel(Label label) {
        this.label = label;
    }

    public Status isStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static int getArtistNum() {
        return artistNum;
    }

    @Override
    public abstract String toString();


}