package com.audiohouse.notarytracker.config;

import com.audiohouse.notarytracker.core.UserCore;
import com.audiohouse.notarytracker.shared.models.internal.UserEntity;
import com.audiohouse.notarytracker.shared.utils.CodecHash;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

public class StartupConfig {

    private UserCore userCore;

    StartupConfig(UserCore userCore) {
        this.userCore = userCore;
        this.createInitAdmin();
    }

    private void createInitAdmin() {
        UserEntity adminUser = new UserEntity();
        adminUser.setUserId(UUID.randomUUID().toString());
        adminUser.setEmail("admin@email.com");
        adminUser.setFirstName("Admin");
        adminUser.setLastName("Account");
        adminUser.setPassword(CodecHash.sha256("admin123!"));
        adminUser.setPhoneNumber("858-000-0000");
        try {
            userCore.getUserByEmail(adminUser.getEmail());
        } catch (ResponseStatusException e) {
            userCore.createUser(adminUser);
        }
    }
}
