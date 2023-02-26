package com.company.label;

import com.company.services.AuditService;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.time.Year;

public class IndieLabel extends Label{

    String runBy = "noname";

    IndieLabel() throws UnknownHostException, FileNotFoundException {
        super();

    }

    public IndieLabel(String name, Year founded, String run_by) throws UnknownHostException, FileNotFoundException {
        super(name, founded);
        this.runBy = run_by;

    }

    @Override
    public String toString(){

        return super.toString() + "Run by: " + runBy + "\n";

    }
}