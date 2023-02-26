package com.company.artist;

import com.company.label.Label;
import com.company.services.AuditService;


import java.io.*;
import java.net.UnknownHostException;
import java.time.Year;

public class Band extends Artist {

    private int members = -1;

    public Band() throws UnknownHostException, FileNotFoundException {
        super();
        AuditService.writeLog("Default Band Object Created");
    }

    public Band(String name, int members, Genre genre, Year debut, Label label, Status status) throws
                UnknownHostException, FileNotFoundException {

        super(name, genre, debut, label, status);
        this.members = members;
        AuditService.writeLog("\"" + name + "\" Band Object Created");
    }


    public int getMembers() {
        return members;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    @Override
    public String toString(){

        return "Band: " + getName() + "\n" +
                "No. of members: " + members + "\n" +
                "Genre: " + getGenre().toString().charAt(0) + getGenre().toString().substring(1).toLowerCase() + "\n" +
                "Debut: " + getDebut() + "\n" +
                "Label: " + getLabel().getName() + "\n" +
                "Status: " + status + "\n";

    }


}