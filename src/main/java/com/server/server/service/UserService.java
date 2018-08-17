package com.server.server.service;

import com.server.server.entity.User;

/**
 * Created by Taras Koloshmatin on 14.08.2018
 */
public interface UserService {

    User createUser(User user);

    User findUserByUsername(String username);

    User findUserByPasswordHash(String passwordHash);

//    boolean findAllByUsernamePassword(String username, String passwordHash);
}