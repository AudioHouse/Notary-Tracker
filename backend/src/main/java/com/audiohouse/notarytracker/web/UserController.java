package com.audiohouse.notarytracker.web;

import com.audiohouse.notarytracker.shared.models.internal.UserEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/user")
public class UserController {

    public ResponseEntity<UserEntity> createUser(
            @RequestBody UserEntity userEntity) {
        return new ResponseEntity<>(userEntity, HttpStatus.CREATED);
    }

}
