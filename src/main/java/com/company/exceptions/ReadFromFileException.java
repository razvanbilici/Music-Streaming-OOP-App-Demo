package com.company.exceptions;

import com.company.services.AuditService;

import java.io.FileNotFoundException;
import java.net.UnknownHostException;

public class ReadFromFileException extends Exception{

    public ReadFromFileException(String message, Throwable cause)

            throws UnknownHostException, FileNotFoundException {
        super(message, cause);
        AuditService.writeLog(cause + " Exception Handled");
    }

    public ReadFromFileException(String message) throws UnknownHostException, FileNotFoundException {
        super(message);
        AuditService.writeLog("Exception Handled");
    }
}

