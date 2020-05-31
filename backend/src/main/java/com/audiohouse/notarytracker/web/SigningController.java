package com.audiohouse.notarytracker.web;

import com.audiohouse.notarytracker.core.SigningCore;
import com.audiohouse.notarytracker.core.TokenCore;
import com.audiohouse.notarytracker.shared.models.internal.SigningEntity;
import com.audiohouse.notarytracker.shared.models.web.PostSigning;
import com.audiohouse.notarytracker.shared.utils.InputValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/signing")
public class SigningController {

    @Autowired
    SigningCore signingCore;

    @Autowired
    TokenCore tokenCore;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<SigningEntity> createSigning(
            @RequestHeader(value = "Authorization") String jwtToken,
            @RequestBody PostSigning postSigning) {
        // authenticate
        tokenCore.throwIfUnauthorized(jwtToken);
        // validate fields
        InputValidator.validatePostSigning(postSigning);
        // create signing
        return new ResponseEntity<>(signingCore.createSigning(postSigning), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<SigningEntity>> getAllSignings(
            @RequestHeader(value = "Authorization") String jwtToken) {
        // authenticate
        tokenCore.throwIfUnauthorized(jwtToken);
        // get all signings
        return new ResponseEntity<>(signingCore.getAllSignings(), HttpStatus.OK);
    }

    @GetMapping(value = "/{signingId}")
    public ResponseEntity<SigningEntity> getSigningById(
            @RequestHeader(value = "Authorization") String jwtToken,
            @PathVariable("signingId") String signingId) {
        // authenticate
        tokenCore.throwIfUnauthorized(jwtToken);
        // get signing by id
        return new ResponseEntity<>(signingCore.getSigningById(signingId), HttpStatus.OK);
    }

}
