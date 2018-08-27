package com.server.server.controller;

import com.server.server.entity.Login;
import com.server.server.entity.User;
import com.server.server.service.UserService;
import com.server.server.utils.HashMD5;
import com.server.server.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private ResponseEntity mResponse;

    @Autowired
    private UserService mService;

    @RequestMapping(value = "/create", method = POST)
    @ResponseBody
    public ResponseEntity createUser(@RequestBody Login login) {
        String username = login.getUsername();
        String password = login.getPassword();
        User newUser = new User();
        byte[] salt;

        if (!isUserExist(username, password, true)) {
            try {
                salt = HashMD5.getSalt();
                newUser.setUsername(username);
                newUser.setUserSalt(String.valueOf(salt));
                newUser.setPasswordHash(HashMD5.getSecurePassword(password, salt));
                mService.createUser(newUser);
                LOG.info(login.toString());
                LOG.info("Create new user: " + newUser.toString());
            } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
                LOG.error(e.getMessage());
            } finally {
                LOG.info("--------------");
            }
        }

        return mResponse != null ? mResponse : ResponseEntity.status(HttpStatus.OK).body(mResult);
    }

    @RequestMapping(value = "/login", method = POST)
    @ResponseBody
    public ResponseEntity login(@RequestBody Login login) {
        isUserExist(login.getUsername(), login.getPassword(), false);
        return mResponse != null ? mResponse : ResponseEntity.status(HttpStatus.OK).body(mResult);
    }

    private boolean isUserExist(String username, String password, boolean isCreateNewUser) {
        User user = mService.findUserByName(username);
        if (user != null) {
            if (isCreateNewUser) {
                LOG.info("Cannot create user. This user " + username + " is already exists!");
                mResult = "Cannot create user. This user " + "<b>" + username + "</b>" + " is already exists!";
                mResponse = ResponseEntity.status(HttpStatus.OK).body(mResult);
                return true;
            } else if (HashMD5.getSecurePassword(password, null).equalsIgnoreCase(user.getPasswordHash())) {
                LOG.info("Success response login.");
                mResult = "Success response login.";
                mResponse = ResponseEntity.status(HttpStatus.OK).body(mResult);
                return true;
            } else {
                LOG.info("Wrong password!");
                mResult = "Wrong password!";
                mResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(mResult);
                return true;
            }
        } else if (user == null && isCreateNewUser) {
            LOG.info("Successful response creating new user.");
            mResult = "Successful response creating new user.";
            mResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(mResult);
            return false;
        } else {
            LOG.info("User absent and == NULL");
            mResult = "User absent and == NULL";
            mResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(mResult);
            return false;
        }
    }
}