package com.audiohouse.notarytracker.web;

import com.audiohouse.notarytracker.core.CoreWorker;
import com.audiohouse.notarytracker.shared.models.web.GetUser;
import com.audiohouse.notarytracker.shared.models.web.PostUser;
import com.audiohouse.notarytracker.shared.utils.EntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    CoreWorker coreWorker;

    @ResponseStatus(value = HttpStatus.CREATED)
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

    @PutMapping(value = "/{userId}")
    public ResponseEntity<GetUser> updateUser(
            @PathVariable("userId") String userId,
            @RequestBody PostUser userToUpdate) {
        return new ResponseEntity<>(EntityTransformer.userEntityToGetUser(
                coreWorker.updateUserById(userId, userToUpdate)), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(
            @PathVariable("userId") String userId) {
        coreWorker.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
