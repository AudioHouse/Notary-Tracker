package com.audiohouse.notarytracker.core;

import com.audiohouse.notarytracker.shared.models.internal.SigningEntity;
import com.audiohouse.notarytracker.shared.models.web.PostSigning;
import com.audiohouse.notarytracker.shared.utils.EntityTransformer;
import com.audiohouse.notarytracker.shared.utils.JavaPickle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SigningCore {

    private static final Logger logger = LoggerFactory.getLogger(SigningCore.class);
    private static final String signingFileLocation = "SigningList.jPickle";

    private JavaPickle jPickle;

    public SigningCore(JavaPickle jPickle) {
        this.jPickle = jPickle;
    }

    public SigningEntity createSigning(PostSigning postSigning) {
        logger.info("Creating signing: {}", postSigning.toString());
        // create signing
        SigningEntity entityToCreate = EntityTransformer.newSigningEntityFromPostSigning(postSigning);
        return jPickle.save(entityToCreate, entityToCreate.getSigningId(), signingFileLocation);
    }
}
