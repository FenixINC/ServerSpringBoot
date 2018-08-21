package com.server.server.repository;

import com.server.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * Created by Taras Koloshmatin on 14.08.2018
 */
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT user FROM User user WHERE user.username = :username")
    User findUserByName(@Param("username") String username);

    @Query("SELECT user FROM User user WHERE user.userSalt = :userSalt")
    User findUserSalt(@Param("userSalt") String userSalt);

//    @Query(
//            value = "SELECT * FROM User u WHERE u.username = :username AND u.passwordHash = :passwordHash",
//            countQuery = "SELECT count(*) FROM user",
//            nativeQuery = true)
//    User findUser(@Param("username") String username,
//                  @Param("passwordHash") String passwordHash);
}
