package com.server.server;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

/**
 * Created by Taras Koloshmatin on 09.08.2018
 */
@SpringBootApplication
public class Application {

    private static Logger mLog = LogManager.getLogger(Application.class);

    public static void main(String[] args) {

        SpringApplication application = new SpringApplication(Application.class);
        application.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
        mLog.debug("---> [Set server.port: 8083]");
        application.run(args);
        mLog.debug("---> [Application is running]");

//        SpringApplication.run(Application.class, args);
    }
}

