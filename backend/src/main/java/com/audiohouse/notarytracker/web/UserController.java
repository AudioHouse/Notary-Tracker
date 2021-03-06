package com.audiohouse.notarytracker.web;

import com.audiohouse.notarytracker.core.SigningCore;
import com.audiohouse.notarytracker.core.TokenCore;
import com.audiohouse.notarytracker.core.UserCore;
import com.audiohouse.notarytracker.shared.models.internal.SigningEntity;
import com.audiohouse.notarytracker.shared.models.web.GetUser;
import com.audiohouse.notarytracker.shared.models.web.PostUser;
import com.audiohouse.notarytracker.shared.utils.EntityTransformer;
import com.audiohouse.notarytracker.shared.utils.InputValidator;
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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserCore userCore;

    @Autowired
    TokenCore tokenCore;

    @Autowired
    SigningCore signingCore;

    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<GetUser> createUser(
            @RequestHeader(value = "Authorization") String jwtToken,
            @RequestBody PostUser postUser) {
        tokenCore.throwIfUnauthorized(jwtToken);
        InputValidator.validatePostUser(postUser);
        return new ResponseEntity<>(EntityTransformer.userEntityToGetUser(
                userCore.createUser(EntityTransformer.postUserToUserEntity(postUser))), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<GetUser>> getAllUsers(
            @RequestHeader(value = "Authorization") String jwtToken) {
        tokenCore.throwIfUnauthorized(jwtToken);
        return new ResponseEntity<>(EntityTransformer.userEntityListToGetUserList(
                userCore.getAllUsers()), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<GetUser> getUser(
            @RequestHeader(value = "Authorization") String jwtToken,
            @PathVariable("userId") String userId) {
        tokenCore.throwIfUnauthorized(jwtToken);
        return new ResponseEntity<>(EntityTransformer.userEntityToGetUser(
                userCore.getUserById(userId)), HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}")
    public ResponseEntity<GetUser> updateUser(
            @RequestHeader(value = "Authorization") String jwtToken,
            @PathVariable("userId") String userId,
            @RequestBody PostUser userToUpdate) {
        tokenCore.throwIfUnauthorized(jwtToken);
        InputValidator.validatePostUser(userToUpdate);
        return new ResponseEntity<>(EntityTransformer.userEntityToGetUser(
                userCore.updateUserById(userId, userToUpdate)), HttpStatus.OK);
    }

    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(
            @RequestHeader(value = "Authorization") String jwtToken,
            @PathVariable("userId") String userId) {
        tokenCore.throwIfUnauthorized(jwtToken);
        userCore.deleteUserById(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/{userId}/signing")
    public ResponseEntity<List<SigningEntity>> getUserSignings(
            @RequestHeader(value = "Authorization") String jwtToken,
            @PathVariable("userId") String userId) {
        // authenticate
        tokenCore.throwIfUnauthorized(jwtToken);
        // check if user exists
        userCore.getUserById(userId);
        // return the list of signings
        return new ResponseEntity<>(signingCore.getSigningsListByNotaryId(userId), HttpStatus.OK);
    }

}
