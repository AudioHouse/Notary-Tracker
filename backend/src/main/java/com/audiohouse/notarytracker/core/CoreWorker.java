package com.audiohouse.notarytracker.core;

import com.audiohouse.notarytracker.shared.models.internal.UserEntity;
import com.audiohouse.notarytracker.shared.models.web.PostUser;
import com.audiohouse.notarytracker.shared.utils.CodecHash;
import com.audiohouse.notarytracker.shared.utils.JavaPickle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CoreWorker {

    private static final Logger logger = LoggerFactory.getLogger(CoreWorker.class);
    private static final String userFileLocation = "UsersList.jPickle";

    private JavaPickle jPickle;

    public CoreWorker(JavaPickle jPickle) {
        this.jPickle = jPickle;
    }

    public UserEntity persistUser(UserEntity userEntity) {
        logger.info("Persisting user: {}", userEntity.toString());
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

}
