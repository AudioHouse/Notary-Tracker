package com.audiohouse.notarytracker.web;

import com.audiohouse.notarytracker.core.CoreWorker;
import com.audiohouse.notarytracker.shared.models.internal.UserEntity;
import com.audiohouse.notarytracker.shared.models.web.PostUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CoreWorker coreWorker;

    @PostMapping
    public ResponseEntity<UserEntity> createUser(
            @RequestBody PostUser postUser) {
        return new ResponseEntity<>(coreWorker.persistUser(
                coreWorker.generateUserEntity(postUser)), HttpStatus.CREATED);
    }

}
