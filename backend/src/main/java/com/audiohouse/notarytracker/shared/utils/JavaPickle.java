package com.audiohouse.notarytracker.shared.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class JavaPickle {

    // Lock object to serialize write operations
    private static final Object lock = new Object();

    public <T> T save(T objectToSave, String objectId, String fileName) {
        synchronized (lock) {
            FileOutputStream fout = null;
            ObjectOutputStream oos = null;
            try {
                fout = new FileOutputStream(fileName);
                oos = new ObjectOutputStream(fout);
                oos.writeObject(objectToSave);
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
            return objectToSave;
        }
    }

}
