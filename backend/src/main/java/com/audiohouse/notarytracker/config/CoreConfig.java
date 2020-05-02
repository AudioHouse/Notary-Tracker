package com.audiohouse.notarytracker.config;

import com.audiohouse.notarytracker.core.CoreWorker;
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
    public CoreWorker coreWorker() {
        return new CoreWorker(javaPickle());
    }

}
