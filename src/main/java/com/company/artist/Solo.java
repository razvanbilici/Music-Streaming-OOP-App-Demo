package com.company.artist;

import com.company.label.Label;
import com.company.services.AuditService;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.time.Year;

public class Solo extends Artist{

    private Year yob = Year.of(-1);

    public Solo() throws UnknownHostException, FileNotFoundException {
        super();
        AuditService.writeLog("Default Solo Artist Object Created");
    }

    public Solo(String name, Year yob, Genre genre, Year debut, Label label,Status status)
            throws UnknownHostException, FileNotFoundException {

        super(name, genre, debut, label, status);
        this.yob = yob;
        AuditService.writeLog("\"" + name + "\" Solo Artist Object Created");
    }

    @Override
    public String toString(){

        return "Artist: " + getName() + "\n" +
                "Year of Birth: " + yob + "\n" +
                "Age: " + (Integer.parseInt(Year.now().toString()) - Integer.parseInt(yob.toString())) + "\n" +
                "Genre: " + getGenre().toString().charAt(0) + getGenre().toString().substring(1).toLowerCase() + "\n" +
                "Debut: " + getDebut() + "\n" +
                "Label: " + getLabel().getName() + "\n" +
                "Status: " + status + "\n";

    }





}