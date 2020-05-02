package com.audiohouse.notarytracker.web;

import com.audiohouse.notarytracker.core.CoreWorker;
import com.audiohouse.notarytracker.shared.models.web.GetUser;
import com.audiohouse.notarytracker.shared.models.web.PostUser;
import com.audiohouse.notarytracker.shared.utils.EntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CoreWorker coreWorker;

    @PostMapping
    public ResponseEntity<GetUser> createUser(
            @RequestBody PostUser postUser) {
        return new ResponseEntity<>(EntityTransformer.userEntityToGetUser(
                coreWorker.persistUser(EntityTransformer.postUserToUserEntity(postUser))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetUser>> getAllUsers() {
        return new ResponseEntity<>(EntityTransformer.userEntityListToGetUserList(
                coreWorker.getAllUsers()), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<GetUser> getUser(
            @PathVariable("userId") String userId) {
        return new ResponseEntity<>(EntityTransformer.userEntityToGetUser(
                coreWorker.getUserById(userId)), HttpStatus.OK);
    }

}
