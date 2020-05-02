package com.audiohouse.notarytracker.shared.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class JavaPickle {

    // Lock object to serialize write operations
    private static final Object lock = new Object();

    public <T> T save(T objectToSave, String objectId, String fileName) {
        // first try to retrieve the current set of objects
        HashMap<String, T> currentSet;
        try {
            currentSet = this.getHashMap(fileName);
            return this.writeToExistingFile(objectToSave, objectId, fileName, currentSet);
        } catch (FileNotFoundException e) {
            // If file does not exist, we will create a new one
            return this.writeToNewFile(objectToSave, objectId, fileName);
        }
    }

    private <T> T writeToExistingFile(T objectToSave, String objectId, String fileName, HashMap<String, T> currentSet) {
        synchronized (lock) {
            FileOutputStream fos;
            ObjectOutputStream oos;
            try {
                fos = new FileOutputStream(fileName);
                oos = new ObjectOutputStream(fos);
                currentSet.put(objectId, objectToSave);
                oos.writeObject(currentSet);
                oos.close();
                oos.close();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
            return objectToSave;
        }
    }

    private <T> T writeToNewFile(T objectToSave, String objectId, String fileName) {
        synchronized (lock) {
            FileOutputStream fos;
            ObjectOutputStream oos;
            HashMap<String, T> newSet = new HashMap<>();
            newSet.put(objectId, objectToSave);
            try {
                fos = new FileOutputStream(fileName);
                oos = new ObjectOutputStream(fos);
                newSet.put(objectId, objectToSave);
                oos.writeObject(newSet);
                oos.close();
                oos.close();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
            return objectToSave;
        }
    }

    public <T> List<T> getAll(String fileName) {
        List<T> listToReturn = new ArrayList<>();
        HashMap<String, T> currentSet;
        try {
            currentSet = this.getHashMap(fileName);
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There are no entries in database");
        }
        currentSet.forEach((key, value) -> listToReturn.add(value));
        return listToReturn;
    }

    private <T> HashMap<String, T> getHashMap(String fileName) throws FileNotFoundException {
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(fileName);
            ois = new ObjectInputStream(fis);
            HashMap<String, T> mapToGet = (HashMap<String, T>) ois.readObject();
            ois.close();
            fis.close();
            return mapToGet;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("The file " + fileName + "doesn't exist yet. " +
                    "A new file should be written");
        } catch (IOException | ClassNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
