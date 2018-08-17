package com.server.server.repository;

import com.server.server.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Taras Koloshmatin on 14.08.2018
 */
public interface UserRepository extends JpaRepository<User, Long> {

//    boolean findAllByUsernamePassword(String username, String passwordHash);

    User findUserByUsername(String username);

    User findUserByPasswordHash(String passwordHash);

//    @Query(
//            value = "SELECT * FROM user WHERE username =:username AND passwordHash =:passwordHash",
//            countQuery = "SELECT count(*) FROM user",
//            nativeQuery = true)
//    User findUser(@Param("username") String username,
//                  @Param("passwordHash") String passwordHash);
}
