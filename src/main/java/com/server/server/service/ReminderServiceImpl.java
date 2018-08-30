package com.server.server.service;

import com.server.server.entity.Remind;
import com.server.server.repository.RemindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReminderServiceImpl implements ReminderService {

    @Autowired
    private RemindRepository repository;

    public List<Remind> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Remind> getListByType(String typeRemind) {
        return repository.getAllByType(typeRemind);
    }

    public Remind getByID(long id) {
        return repository.getOne(id);
    }

    public Remind create(Remind remind) {
        return repository.saveAndFlush(remind);
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Remind isRemindExists(String title, String description) {
        return repository.isRemindExists(title, description);
    }
}