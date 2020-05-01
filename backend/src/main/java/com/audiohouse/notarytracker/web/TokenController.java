package com.audiohouse.notarytracker.web;

import com.audiohouse.notarytracker.shared.models.web.PostToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/token")
public class TokenController {

    @PostMapping
    public ResponseEntity<String> createToken(
            @RequestBody PostToken postToken) {
        return new ResponseEntity<>("Your new token!", HttpStatus.CREATED);
    }

}
