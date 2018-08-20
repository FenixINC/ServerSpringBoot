package com.server.server.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_remind", schema = "myapp")
public class Remind {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Column(name = "title", length = 150)
    private String title;

    @Column(name = "remind_date")
    private String remindDate;

    @Column(name = "description", length = 1024)
    private String description;

    @Column(name = "type_remind")
    private String typeRemind;
}