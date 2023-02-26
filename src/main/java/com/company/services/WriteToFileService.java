package com.company.services;


import java.io.*;
import com.company.artist.Band;
import com.company.services.ReadFromFileService;
import com.company.exceptions.*;

import java.io.*;
import java.net.UnknownHostException;
import java.util.Arrays;

public class WriteToFileService {

    private static String bandCsvConverter (Band band){

        return band.getName() + "," +

                band.getMembers() + "," +

                band.getGenre() + "," +

                band.getDebut().toString() + "," +

                band.getLabel().getName().toUpperCase() + "," +

                band.isStatus();

    }

    public static void writeBand(Band band, String file) throws FileNotFoundException,
            UnknownHostException, ReadFromFileException {

        String bandCsvFormat = bandCsvConverter(band);

        for(String[] i : ReadFromFileService.csvFileRead(file)){

            if (Arrays.toString(i).replace("[", "").replace("]", "").
                    replace(" ", "").equals(bandCsvFormat)) {

                System.out.println("Band " + band.getName() + " already in CSV file\n");
                AuditService.writeLog("Band " + band.getName() + " not added - already in CSV file");

                return;
            }

        }

        try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))) {
            pw.append(bandCsvFormat);
            pw.append("\n");
        }
    }

    }

