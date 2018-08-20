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
    public void createUser(User user) {
        repository.saveAndFlush(user);
    }

    @Override
    public User findUser(String username) {
        return repository.findUser(username);
    }
}