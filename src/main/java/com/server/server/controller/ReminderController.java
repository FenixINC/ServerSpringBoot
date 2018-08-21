package com.server.server.controller;

import com.server.server.entity.Remind;
import com.server.server.service.ReminderService;
import com.server.server.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

    private static final LoggerUtils LOG = new LoggerUtils(ReminderController.class);

    @Autowired
    private ReminderService service;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public List<Remind> getAllReminders() {
        LOG.info("getAllReminds");
        return service.getAll();
    }

    @RequestMapping(value = "/get-by-id", params = "id", method = GET)
    @ResponseBody
    public Remind getReminder(@RequestParam(value = "id") long remindID) {
        LOG.info("getById: id = " + remindID);
        return service.getByID(remindID);
    }

    @RequestMapping(value = "/get-by-type", params = "type", method = GET)
    @ResponseBody
    public List<Remind> getListByType(@RequestParam(value = "type") String typeRemind) {
        LOG.info("getListByType: type = " + typeRemind);
        return service.getListByType(typeRemind);
    }

    @RequestMapping(value = "/createReminder", method = POST)
    @ResponseBody
    public Remind createReminder(@RequestBody Remind remind) {
        LOG.info("createReminder: " + remind.toString());
        return service.create(remind);
    }

    @RequestMapping(value = "/delete", params = "id", method = DELETE)
    @ResponseBody
    public void delete(@RequestParam(value = "id") long remindID) {
        LOG.info("delete: id = " + remindID);
        service.delete(remindID);
    }

    // @PathVariable == @Path
//    @RequestMapping(value = "/delete/{id}", method = DELETE)
//    @ResponseBody
//    public void delete(@PathVariable long id) {
//        service.delete(id);
//    }
}