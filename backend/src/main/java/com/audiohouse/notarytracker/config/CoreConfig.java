package com.audiohouse.notarytracker.config;

import com.audiohouse.notarytracker.core.SigningCore;
import com.audiohouse.notarytracker.core.TokenCore;
import com.audiohouse.notarytracker.core.UserCore;
import com.audiohouse.notarytracker.shared.utils.JavaPickle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreConfig {

    @Bean
    public JavaPickle javaPickle() {
        return new JavaPickle();
    }

    @Bean
    public UserCore coreWorker() {
        return new UserCore(javaPickle());
    }

    @Bean
    public TokenCore tokenCore() {
        return new TokenCore();
    }

    @Bean
    public SigningCore signingCore() { return new SigningCore(javaPickle()); }

    @Bean
    public StartupConfig startupConfig() {
        return new StartupConfig(coreWorker());
    }

}
