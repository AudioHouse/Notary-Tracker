package com.audiohouse.notarytracker.core;

import com.audiohouse.notarytracker.shared.models.internal.UserEntity;
import com.audiohouse.notarytracker.shared.models.web.PostUser;
import com.audiohouse.notarytracker.shared.utils.CodecHash;
import com.audiohouse.notarytracker.shared.utils.JavaPickle;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.UUID;

public class CoreWorker {

    private static final Logger logger = LoggerFactory.getLogger(CoreWorker.class);
    private static final String userFileLocation = "UsersList.jPickle";

    private JavaPickle jPickle;

    public CoreWorker(JavaPickle jPickle) {
        this.jPickle = jPickle;
    }

    public UserEntity generateUserEntity(PostUser postUser) {
        UserEntity userToGenerate = new UserEntity();
        userToGenerate.setUserId(UUID.randomUUID().toString());
        userToGenerate.setFirstName(postUser.getFirstName());
        userToGenerate.setLastName(postUser.getLastName());
        userToGenerate.setEmail(postUser.getEmail());
        userToGenerate.setPassword(CodecHash.sha256(postUser.getPassword()));
        userToGenerate.setPhoneNumber(postUser.getPhoneNumber());
        return userToGenerate;
    }

    public UserEntity persistUser(UserEntity userEntity) {
        logger.info("Persisting user: {}", userEntity);
        return jPickle.save(userEntity, userEntity.getUserId(), userFileLocation);
    }

    public List<UserEntity> getAllUsers() {
        logger.info("Getting all users");
        return jPickle.getAll(userFileLocation);
    }

}
