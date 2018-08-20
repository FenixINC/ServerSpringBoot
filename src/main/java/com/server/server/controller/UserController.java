package com.server.server.controller;

import com.server.server.entity.Login;
import com.server.server.entity.User;
import com.server.server.service.UserService;
import com.server.server.utils.HashMD5;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

/**
 * Created by Taras Koloshmatin on 14.08.2018
 */
@RestController
@RequestMapping("/login")
public class UserController {

    private static final Logger LOG = LoggerFactory.getLogger(ReminderController.class);

    @Autowired
    private UserService mService;

    @RequestMapping(value = "/create-user", method = POST)
    @ResponseBody
    public String createUser(@RequestBody Login login) {
        String result = "false";
        String username = login.getUsername();
        String password = login.getPassword();
        User newUser = new User();
        byte[] salt;

        try {
            //TODO: implement check login user by username, password

//            if (isUserExist(username, password)) {
                salt = HashMD5.getSalt();
                newUser.setUsername(username);
                newUser.setUserSalt(String.valueOf(salt));
                newUser.setPasswordHash(HashMD5.getSecurePassword(password, salt));
                mService.createUser(newUser);
//            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            LOG.error("[ERROR] ---> [" + e.getMessage() + "]");
        }
        LOG.info("[INFO] ---> [" + login.toString() + "]");
        LOG.info("[INFO] ---> [create new user: " + newUser.toString() + "]");

        return result;
    }

    private boolean isUserExist(String username, String passwordHash) {
        User user = mService.findUser(username);
        if (user != null) {
            LOG.info("[INFO] ---> [" + "User exists." + "]");
            return true;
        } else {
            LOG.info("[INFO] ---> [" + "User absents!" + "]");
            return false;
        }
    }
}