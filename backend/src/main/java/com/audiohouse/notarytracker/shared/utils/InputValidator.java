package com.audiohouse.notarytracker.shared.utils;

import com.audiohouse.notarytracker.shared.models.web.PostUser;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.Field;

public class InputValidator {

    public static void validatePostUser(PostUser postUserToValidate) {
        genericNullMemberStringChecker(postUserToValidate);
        genericEmptyMemberStringChecker(postUserToValidate);
    }

    private static void genericEmptyMemberStringChecker(Object objectToCheck) {
        for (Field f: Object.class.getDeclaredFields()) {
            if (f.toString().isEmpty()) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "The following field cannot be blank: " + f.getName());
            }
        }
    }

    private static void genericNullMemberStringChecker(Object objectToCheck) {
        for (Field f: Object.class.getDeclaredFields()) {
            try {
                if (f.get(objectToCheck) == null) {
                    throw new ResponseStatusException(
                            HttpStatus.BAD_REQUEST, "The following field cannot be null: " + f.getName());
                }
            } catch (IllegalAccessException e) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "The following field cannot be null: " + f.getName());
            }
        }
    }

}
