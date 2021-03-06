package com.server.server;

import com.server.server.utils.LoggerUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Created by Taras Koloshmatin on 09.08.2018
 */
@SpringBootApplication
public class Application {

    private static final LoggerUtils LOG = new LoggerUtils(Application.class);

    public static void main(String[] args) {

//        SpringApplication application = new SpringApplication(Application.class);
//        application.setDefaultProperties(Collections.singletonMap("server.port", "8083"));
//        LOG.info("[INFO] ---> [Set server.port: 8083]");
//        application.run(args);
//        LOG.info("[INFO] ---> [Application is running]");

        SpringApplication.run(Application.class, args);
        LOG.info("Application is running, port: 8080");
    }
}