package com.audiohouse.notarytracker.shared.utils;

import com.audiohouse.notarytracker.shared.models.internal.SigningEntity;
import com.audiohouse.notarytracker.shared.models.internal.UserEntity;
import com.audiohouse.notarytracker.shared.models.web.GetUser;
import com.audiohouse.notarytracker.shared.models.web.PostSigning;
import com.audiohouse.notarytracker.shared.models.web.PostUser;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class EntityTransformer {

    public static UserEntity postUserToUserEntity(PostUser postUser) {
        UserEntity userToGenerate = new UserEntity();
        userToGenerate.setUserId(UUID.randomUUID().toString());
        userToGenerate.setFirstName(postUser.getFirstName());
        userToGenerate.setLastName(postUser.getLastName());
        userToGenerate.setEmail(postUser.getEmail());
        userToGenerate.setPassword(CodecHash.sha256(postUser.getPassword()));
        userToGenerate.setPhoneNumber(postUser.getPhoneNumber());
        return userToGenerate;
    }

    public static GetUser userEntityToGetUser(UserEntity userEntity) {
        GetUser userToReturn = new GetUser();
        userToReturn.setUserId(userEntity.getUserId());
        userToReturn.setFirstName(userEntity.getFirstName());
        userToReturn.setLastName(userEntity.getLastName());
        userToReturn.setEmail(userEntity.getEmail());
        userToReturn.setPhoneNumber(userEntity.getPhoneNumber());
        return userToReturn;
    }

    public static List<GetUser> userEntityListToGetUserList(List<UserEntity> userEntities) {
        List<GetUser> userListToReturn = new ArrayList<>();
        userEntities.forEach(item->userListToReturn.add(userEntityToGetUser(item)));
        return userListToReturn;
    }

    public static SigningEntity newSigningEntityFromPostSigning(PostSigning postSigning) {
        SigningEntity signingEntityToGenerate = new SigningEntity();
        signingEntityToGenerate.setSigningId(UUID.randomUUID().toString());
        signingEntityToGenerate.setAdditionalNotes(postSigning.getAdditionalNotes());
        signingEntityToGenerate.setAssignedNotaryId("None");
        signingEntityToGenerate.setAssignedNotaryName("None");
        signingEntityToGenerate.setClientPhoneNumber(postSigning.getClientPhoneNumber());
        signingEntityToGenerate.setClientSurname(postSigning.getClientSurname());
        signingEntityToGenerate.setCreatedDate(DateHelper.getDateNow());
        signingEntityToGenerate.setLastUpdatedDate(DateHelper.getDateNow());
        signingEntityToGenerate.setEscrowCompanyName(postSigning.getEscrowCompanyName());
        signingEntityToGenerate.setEscrowOfficerName(postSigning.getEscrowOfficerName());
        signingEntityToGenerate.setSigningTimeDate(postSigning.getSigningTimeDate());
        signingEntityToGenerate.setZipCodeArea(postSigning.getZipCodeArea());
        return signingEntityToGenerate;
    }

}
