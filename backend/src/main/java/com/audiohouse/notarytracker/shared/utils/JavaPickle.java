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

/**
 * A class to write objects to local files.
 * This is just for testing purposes. A production
 * system would write to a database instead.
 */
public class JavaPickle {

    // Lock object to serialize write operations
    private static final Object lock = new Object();

    public <T> T save(T objectToSave, String objectId, String fileName) {
        synchronized (lock) {
            HashMap<String, T> currentSet;
            try {
                // first try to retrieve the current set of objects
                currentSet = this.getHashMap(fileName);
            } catch (FileNotFoundException e) {
                // If file does not exist, we will create a new one
                currentSet = new HashMap<>();
                currentSet.put(objectId, objectToSave);
            }
            return this.writeFile(objectToSave, objectId, fileName, currentSet);
        }
    }

    public <T> List<T> getAll(String fileName) {
        List<T> listToReturn = new ArrayList<>();
        HashMap<String, T> currentSet;
        try {
            currentSet = this.getHashMap(fileName);
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There are no entries in database");
        }
        currentSet.forEach((key, value) -> listToReturn.add(value));
        return listToReturn;
    }

    public <T> T getById(String id, String filename) {
        try {
            T objectToReturn =  (T) this.getHashMap(filename).get(id);
            if (objectToReturn != null) {
                return objectToReturn;
            } else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Did not find object with id: " + id);
            }
        } catch (FileNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "There are no entries in database");
        }
    }

    public <T> void deleteById(String id, String filename) {
        synchronized (lock) {
            try {
                HashMap<String, T> objectHashMap = this.getHashMap(filename);
                if (objectHashMap.get(id) != null) {
                    objectHashMap.remove(id);
                    this.writeFile(filename, objectHashMap);
                } else {
                    throw new ResponseStatusException(
                            HttpStatus.NOT_FOUND, "Did not find object with id: " + id);
                }
            } catch (FileNotFoundException e) {
                throw new ResponseStatusException(
                        HttpStatus.UNPROCESSABLE_ENTITY, "There are no entries in database");
            }
        }
    }

    private <T> T writeFile(T objectToSave, String objectId, String fileName, HashMap<String, T> currentSet) {
        synchronized (lock) {
            FileOutputStream fos;
            ObjectOutputStream oos;
            try {
                fos = new FileOutputStream(fileName);
                oos = new ObjectOutputStream(fos);
                currentSet.put(objectId, objectToSave);
                oos.writeObject(currentSet);
                oos.close();
                fos.close();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
            return objectToSave;
        }
    }

    private <T> void writeFile(String filename, HashMap<String, T> currentSet) {
        synchronized (lock) {
            FileOutputStream fos;
            ObjectOutputStream oos;
            try {
                fos = new FileOutputStream(filename);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(currentSet);
                oos.close();
                fos.close();
            } catch (IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }
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
