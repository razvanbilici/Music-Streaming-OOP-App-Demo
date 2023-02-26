package com.company.services;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.net.UnknownHostException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditService {

    public static void writeLog(String action) throws FileNotFoundException, UnknownHostException {

        String user = System.getProperty("user.name");


        try (PrintWriter pw = new PrintWriter(new FileOutputStream("log.txt", true))) {

            DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            LocalDateTime now = LocalDateTime.now();


            pw.append("User: ").append(user).append(" - ");
            pw.append(dataFormat.format(now)).append(" - ");
            pw.append(action).append("\n");
        }
    }

}
