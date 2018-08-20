package com.server.server.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Taras Koloshmatin on 14.08.2018
 */
@Data
@Entity
@Table(name = "tbl_user", schema = "myapp")
public class User {

    //    @GeneratedValue(generator = "increment")
    //    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "increment", strategy = "increment")
    @Column(name = "id")
    private long id;

    @Column(name = "username", length = 50)
    private String username;

    @Column(name = "userSalt", length = 1024)
    private String userSalt;

    @Column(name = "passwordHash", length = 1024)
    private String passwordHash;
}