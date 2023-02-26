package com.company.services;

import com.company.artist.Band;
import com.company.artist.Genre;
import com.company.artist.Status;
import com.company.exceptions.ReadFromFileException;
import com.company.label.Label;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.UnknownHostException;
import java.time.Year;
import java.util.ArrayList;

public class ReadFromFileService {

    public static ArrayList<String[]> csvFileRead(String file) throws
            ReadFromFileException, UnknownHostException, FileNotFoundException {

        ArrayList<String[]> values = new ArrayList<>();

        try (BufferedReader buffer = new BufferedReader(new FileReader(file))) {

            String line = "";

            while ((line = buffer.readLine()) != null) {

                String[] row = line.split(",");
                values.add(row);

            }

        } catch (IOException e) {
            throw new ReadFromFileException("Buffer File Reader Method Error!", e);
        }

        return values;
    }

    public static Band createBandFromCsv(String csvFile, String bandName)
            throws ReadFromFileException, UnknownHostException, FileNotFoundException {

        int index = -1;

        ArrayList <String[]> bandData = csvFileRead(csvFile);

        for(int i = 0; i < bandData.size(); i++) {

            if (bandName.equalsIgnoreCase(bandData.get(i)[0])) {
                index = i;
                break; }

        }

        if (index == -1){

            System.out.println("Band \"" + bandName + "\" Not Found In Specified CSV File\n");

            AuditService.writeLog("Couldn't Create Band Object " + bandName + " - \nBand name " +
                    "not found in specified CSV file - Default Band Object Created Instead\n");

            return new Band();

        }

        Band band = new Band();

        AuditService.writeLog("Band Object \"" + bandName + "\" Created Successfully From Specified CSV File");

        band.setName(bandData.get(index)[0]);
        band.setMembers(Integer.parseInt(bandData.get(index)[1]));
        band.setGenre(Genre.valueOf(bandData.get(index)[2]));
        band.setDebut(Year.of(Integer.parseInt(bandData.get(index)[3])));

        for (Label label : LabelPoolService.getLabelPool()){

            if (label.getName().equalsIgnoreCase(bandData.get(index)[4])) {

                band.setLabel(label);
                break; }

        }


        band.setStatus(Status.valueOf(bandData.get(index)[5]));

        return band;
    }
}
