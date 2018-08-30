package com.server.server.service;

import com.server.server.entity.Remind;

import java.util.List;

public interface ReminderService {

    List<Remind> getAll();

    List<Remind> getListByType(String typeRemind);

    Remind getByID(long id);

    Remind create(Remind remind);

    void delete(long id);

    Remind isRemindExists(String title, String description);
}