package com.audiohouse.notarytracker.web;

import com.audiohouse.notarytracker.core.TokenCore;
import com.audiohouse.notarytracker.core.UserCore;
import com.audiohouse.notarytracker.shared.models.web.GetUser;
import com.audiohouse.notarytracker.shared.models.web.PostToken;
import com.audiohouse.notarytracker.shared.models.web.ReturnToken;
import com.audiohouse.notarytracker.shared.utils.EntityTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/token")
public class TokenController {

    @Autowired
    TokenCore tokenCore;

    @Autowired
    UserCore userCore;

    @PostMapping
    public ResponseEntity<ReturnToken> createToken(
            @RequestBody PostToken postToken) {
        // verify user exists
        userCore.authenticateUser(postToken.getEmail(), postToken.getPassword());
        // create token
        String token = tokenCore.generateJWT(
                UUID.randomUUID().toString(),
                userCore.getUserByEmail(postToken.getEmail()).getUserId());
        ReturnToken tokenResponse = new ReturnToken();
        tokenResponse.setAuthToken(token);
        return new ResponseEntity<>(tokenResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<GetUser> validateToken(
            @RequestHeader(value = "Authorization") String jwtToken) {
        String userId = tokenCore.getUserIdFromToken(jwtToken);
        return new ResponseEntity<>(
                EntityTransformer.userEntityToGetUser(userCore.getUserById(userId)), HttpStatus.ACCEPTED);
    }

}
