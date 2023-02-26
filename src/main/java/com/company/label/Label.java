package com.company.label;

import com.company.services.AuditService;
import com.company.services.LabelPoolService;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.time.Year;

public class Label {

    private String labelId = "xxDefault";

    private String name = "noname";
    private Year est = Year.of(-1);   // *established* est.

    static int labelNum = 0;


    public Label() throws UnknownHostException, FileNotFoundException {

        labelNum++;
        LabelPoolService.add(this);
        AuditService.writeLog("Default Label Object Created");

    }

    public Label(String name, Year founded) throws UnknownHostException, FileNotFoundException {

        labelNum++;
        LabelPoolService.add(this);

        this.name = name;
        this.est = founded;

        labelId = labelNum + "xx" + this.name + est;
        AuditService.writeLog("\"" + name + "\" Label Object Created");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Year getEst() {
        return est;
    }

    public void setEst(Year est) {
        this.est = est;
    }

    public static int getLabelNum() {
        return labelNum;
    }

    public String getLabel_id() {
        return labelId;
    }


    public String toString() {
        return "Label: " + name + "\n" +
                "Est.: " + est + "\n";
    }
}