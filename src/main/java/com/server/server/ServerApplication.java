package com.server.server;

import org.jboss.jandex.Main;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Collections;

@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(Main.class);
        application.setDefaultProperties(Collections.singletonMap("server.port", "8083"));

        application.run(args);
    }
}
