package com.server.server.service;

import com.server.server.entity.User;
import com.server.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Taras Koloshmatin on 14.08.2018
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User createUser(User user) {
        return repository.saveAndFlush(user);
    }

    @Override
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public User findUserByPasswordHash(String passwordHash) {
        return repository.findUserByPasswordHash(passwordHash);
    }

//    @Override
//    public boolean findAllByUsernamePassword(String username, String passwordHash) {
//        return repository.findAllByUsernamePassword(username, passwordHash);
//    }
}