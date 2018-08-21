package com.server.server.service;

import com.server.server.entity.User;

/**
 * Created by Taras Koloshmatin on 14.08.2018
 */
public interface UserService {

    void createUser(User user);

    User findUserByName(String username);

    User findUserSalt(String userSalt);
}