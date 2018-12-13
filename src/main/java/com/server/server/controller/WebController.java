package com.server.server.controller;

import com.server.server.entity.User;
import com.server.server.utils.LoggerUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Taras Koloshmatin on 11.12.2018
 */
@RestController
@RequestMapping("/test")
public class WebController {

    private static final LoggerUtils LOG = new LoggerUtils(WebController.class);

    @RequestMapping(method = POST)
    public String processRegistration(@ModelAttribute("userForm") User user,
                                      Map<String, Object> model) {

        // implement your own registration logic here...

        // for testing purpose:
//        System.out.println("username: " + user.getUsername());
//        System.out.println("password: " + user.getPassword());
//        System.out.println("email: " + user.getEmail());
//        System.out.println("birth date: " + user.getBirthDate());
//        System.out.println("profession: " + user.getProfession());

        return "RegistrationSuccess";
    }
}
