package com.server.server.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {
    @RequestMapping("/get")
    public String sayHello() {
        return "agasegerh";
    }
}