package com.server.server.repository;

import com.server.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Taras Koloshmatin on 14.08.2018
 */
public interface UserRepository extends JpaRepository<User, Long> {

    boolean isExists(String username, String password);
}
