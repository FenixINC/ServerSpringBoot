package com.server.server.service;

import com.server.server.entity.Remind;

import java.util.List;

public interface ReminderService {

    Remind create(Remind remind);

    void delete(Remind remind);

    List<Remind> getAll();

    List<Remind> getListByType(String typeRemind);

    Remind getByID(long id);

    Remind isRemindExists(String title, String description);
}