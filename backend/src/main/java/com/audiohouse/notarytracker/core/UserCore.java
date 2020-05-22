package com.audiohouse.notarytracker.core;

import com.audiohouse.notarytracker.shared.models.internal.UserEntity;
import com.audiohouse.notarytracker.shared.models.web.PostUser;
import com.audiohouse.notarytracker.shared.utils.CodecHash;
import com.audiohouse.notarytracker.shared.utils.JavaPickle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class UserCore {

    private static final Logger logger = LoggerFactory.getLogger(UserCore.class);
    private static final String userFileLocation = "UsersList.jPickle";

    private JavaPickle jPickle;

    public UserCore(JavaPickle jPickle) {
        this.jPickle = jPickle;
    }

    public UserEntity createUser(UserEntity userEntity) {
        logger.info("Persisting user: {}", userEntity.toString());
        // make sure the user doesn't already exist
        throwIfEmailExists(userEntity.getEmail());
        // persist new user
        return jPickle.save(userEntity, userEntity.getUserId(), userFileLocation);
    }

    public List<UserEntity> getAllUsers() {
        logger.info("Getting all users");
        return jPickle.getAll(userFileLocation);
    }

    public UserEntity getUserById(String id) {
        logger.info("Getting user with id: {}", id);
        return jPickle.getById(id, userFileLocation);
    }

    public UserEntity getUserByEmail(String email) {
        logger.info("Getting user with email: {}", email);
        List<UserEntity> usersList = jPickle.getAll(userFileLocation);
        for (UserEntity user : usersList) {
            if (email.equals(user.getEmail())) {
                return user;
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "User with email '" + email + "' does not exist");
    }

    public UserEntity updateUserById(String id, PostUser postUser) {
        logger.info("Updating user {} with info {}", id, postUser.toString());
        UserEntity userToUpdate = jPickle.getById(id, userFileLocation);
        userToUpdate.setFirstName(postUser.getFirstName());
        userToUpdate.setLastName(postUser.getLastName());
        userToUpdate.setEmail(postUser.getEmail());
        userToUpdate.setPassword(CodecHash.sha256(postUser.getPassword()));
        userToUpdate.setPhoneNumber(postUser.getPhoneNumber());
        return jPickle.save(userToUpdate, userToUpdate.getUserId(), userFileLocation);
    }

    public void deleteUserById(String id) {
        logger.info("Deleting user with id: {}", id);
        jPickle.deleteById(id, userFileLocation);
    }

    public Boolean authenticateUser(String email, String password) {
        logger.info("Authenticating user with email: {}", email);
        List<UserEntity> usersList = jPickle.getAll(userFileLocation);
        for (UserEntity user : usersList) {
            if ((email.equals(user.getEmail()) && (CodecHash.sha256(password).equals(user.getPassword())))) {
                return true;
            }
        }
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Email and password combination incorrect.");
    }

    public void throwIfEmailExists(String email) {
        logger.info("Checking if email {} is taken", email);
        List<UserEntity> usersList;
        try {
            usersList = jPickle.getAll(userFileLocation);
        } catch (ResponseStatusException ex) {
            return;
        }
        for (UserEntity user : usersList) {
            if (email.equals(user.getEmail())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT, "A user with email '" + email +"' already exists.");
            }
        }
    }

}
