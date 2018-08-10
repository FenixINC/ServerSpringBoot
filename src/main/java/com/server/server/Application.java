package com.server.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

/**
 * Created by Taras Koloshmatin on 09.08.2018
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Application.class);
        application.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
        application.run(args);

//        SpringApplication.run(Application.class, args);
    }
}

