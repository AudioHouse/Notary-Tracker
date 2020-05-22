package com.audiohouse.notarytracker.core;

import com.audiohouse.notarytracker.shared.utils.JavaPickle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SigningCore {

    private static final Logger logger = LoggerFactory.getLogger(SigningCore.class);
    private static final String userFileLocation = "SigningList.jPickle";

    private JavaPickle jPickle;

    public SigningCore(JavaPickle jPickle) {
        this.jPickle = jPickle;
    }



}
