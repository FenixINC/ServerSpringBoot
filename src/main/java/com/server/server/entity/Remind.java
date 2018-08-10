package com.server.server.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "remind")
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

    @Override
    public String toString() {
        return "Remind{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", remindDate='" + remindDate + '\'' +
                ", description='" + description + '\'' +
                ", typeRemind='" + typeRemind + '\'' +
                '}';
    }

    @Column(name = "type_remind")
    private String typeRemind;

    public Remind() {
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getRemindDate() {
        return remindDate;
    }

    public String getDescription() {
        return description;
    }

    public String getTypeRemind() {
        return typeRemind;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRemindDate(String remindDate) {
        this.remindDate = remindDate;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTypeRemind(String typeRemind) {
        this.typeRemind = typeRemind;
    }
}
