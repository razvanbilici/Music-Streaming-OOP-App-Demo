package com.company.services;

import com.company.label.Label;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;
import java.util.ArrayList;

//Am folosit clasa asta in ReadFromFileService la functia createBandFromCsv,
//in punctul in care vreau sa setez labelul

//In principiu, la etapa de setLabel(), se cauta in labelPool un label al carui nume coincide cu
//numele labelului din fisierul CVS, si daca este gasit se apeleaza setLabel(*labelul gasit*)

public class LabelPoolService {

    private static ArrayList<Label> labelPool = new ArrayList<>();

    private static LabelPoolService instance;

    static {
        try {
            instance = new LabelPoolService();
            AuditService.writeLog("Label Pool Singleton Accessed");
        } catch (UnknownHostException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private LabelPoolService() throws UnknownHostException, FileNotFoundException {

        AuditService.writeLog("Label Pool Singleton Accessed");

    }

    public static LabelPoolService getInstance() {

        return instance;

    }

    static public void add(Label label){

        labelPool.add(label);

    }

    public static ArrayList<Label> getLabelPool() {
        return labelPool;
    }

    public static void setLabelPool(ArrayList<Label> labelPool) {
        LabelPoolService.labelPool = labelPool;
    }
}
