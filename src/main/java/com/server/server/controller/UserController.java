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

    @RequestMapping(value = "/create-user", method = POST)
    @ResponseBody
    public ResponseEntity createUser(@RequestBody Login login) {
        String username = login.getUsername();
        String password = login.getPassword();
        User newUser = new User();
        byte[] salt;

        try {
            //TODO: implement check login user by username, password

            if (!isUserExist(username, password)) {
                salt = HashMD5.getSalt();
                newUser.setUsername(username);
                newUser.setUserSalt(String.valueOf(salt));
                newUser.setPasswordHash(HashMD5.getSecurePassword(password, salt));
                mService.createUser(newUser);
                LOG.info("Create new user: " + newUser.toString());
                LOG.info("--------------");
            } else {
                LOG.info("User exists. " + mResult);
                LOG.info("--------------");
            }
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            LOG.error(e.getMessage());
        }
        LOG.info(login.toString());
        LOG.info(mResult);
        LOG.info("--------------");
        return ResponseEntity.status(HttpStatus.OK).body(mResult);
    }

    private boolean isUserExist(String username, String password) {
        User user = mService.findUserByName(username);
        if (user != null) {
            if (HashMD5.getSecurePassword(password, null).equalsIgnoreCase(user.getPasswordHash())) {
                mResult = "Success login.";
                LOG.info("Success login.");
                return true;
            } else {
                mResult = "Wrong password!";
                LOG.info("Wrong password!");
                return true;
            }
        } else {
            mResult = "User absent and == NULL";
            LOG.info("User absent and == NULL");
            LOG.info("--------------");
            return false;
        }
//        if (user != null) {
//            User compareUser = mService.findUserSalt(user.getUserSalt());
//            LOG.info(compareUser.getUserSalt());
//            LOG.info(HashMD5.getSecurePassword(password, compareUser.getUserSalt().getBytes()));
//            if (compareUser.getPasswordHash().equalsIgnoreCase(HashMD5.getSecurePassword(password, compareUser.getUserSalt().getBytes()))) {
//                mResult = "Success login.";
////                mResponse = ResponseEntity.status(HttpStatus.OK).body(mResult);
//                LOG.info("Success login. Status code: ");
//                LOG.info("--------------");
//                return true;
//            } else {
//                mResult = "Wrong password!";
////                mResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(mResult);
//                LOG.info("Wrong password! Status code: "/* + mResponse.getStatusCode()*/);
//                LOG.info("--------------");
//                return false;
//            }
//        } else {
//            LOG.info("User absent and == NULL");
//            LOG.info("--------------");
//            mResult = "false";
//            return false;
//        }
    }
}