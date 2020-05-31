package com.audiohouse.notarytracker.core;

import com.audiohouse.notarytracker.shared.models.internal.SigningEntity;
import com.audiohouse.notarytracker.shared.models.web.PostSigning;
import com.audiohouse.notarytracker.shared.utils.EntityTransformer;
import com.audiohouse.notarytracker.shared.utils.JavaPickle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

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

    public SigningEntity getSigningById(String signingId) {
        logger.info("Getting signing by id: {}", signingId);
        // get signing by id
        return jPickle.getById(signingId, signingFileLocation);
    }

    public List<SigningEntity> getAllSignings() {
        logger.info("Getting all signings");
        return jPickle.getAll(signingFileLocation);
    }

    public List<SigningEntity> getSigningsListByNotaryId(String notaryId) {
        logger.info("Getting list of signings with notary id: {}", notaryId);
        List<SigningEntity> listToReturn = new ArrayList<>();
        // get all signings
        List<SigningEntity> listOfAllSignings = jPickle.getAll(signingFileLocation);
        // iterate through and grab matching ones
        for (SigningEntity signing : listOfAllSignings) {
            if (signing.getAssignedNotaryId().equals(notaryId)) {
                listToReturn.add(signing);
            }
        }
        return listToReturn;
    }
}
