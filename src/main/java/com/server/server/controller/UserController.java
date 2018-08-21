package com.server.server.controller;

import com.server.server.entity.Login;
import com.server.server.entity.User;
import com.server.server.service.UserService;
import com.server.server.utils.HashMD5;
import com.server.server.utils.LoggerUtils;
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

    private static final LoggerUtils LOG = new LoggerUtils(UserController.class);
    private String mResult = "false";

    @Autowired
    private UserService mService;

    @RequestMapping(value = "/create-user", method = POST)
    @ResponseBody
    public String createUser(@RequestBody Login login) {
        String username = login.getUsername();
        String password = login.getPassword();
        User newUser = new User();
        byte[] salt;

        try {
            //TODO: implement check login user by username, password

            if (!isUserExist(username)) {
                salt = HashMD5.getSalt();
                newUser.setUsername(username);
                newUser.setUserSalt(String.valueOf(salt));
                newUser.setPasswordHash(HashMD5.getSecurePassword(password, salt));
                mService.createUser(newUser);
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            LOG.error(e.getMessage());
        }
        LOG.info(login.toString());
        LOG.info("create new user: " + newUser.toString());

        return mResult;
    }

    private boolean isUserExist(String username) {

        User user = mService.findUserByName(username);
        if (user != null) {
            User salt = mService.findUserSalt(user.getUserSalt());
            if (salt != null && user.getUserSalt().equalsIgnoreCase(salt.getUserSalt())) {
                LOG.info("User exists.");
                mResult = "true";
                return true;
            } else {
                LOG.info("User salt doesn't match!");
                mResult = "false";
                return false;
            }
        } else {
            LOG.info("User absents!");
            mResult = "false";
            return false;
        }
    }
}