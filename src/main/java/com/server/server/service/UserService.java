package com.server.server.service;

import com.server.server.entity.User;

/**
 * Created by Taras Koloshmatin on 14.08.2018
 */
public interface UserService {

    User createUser(User user);

    boolean isUserExist(String username, String password);
}