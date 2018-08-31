package com.server.server.controller;

import com.server.server.entity.Remind;
import com.server.server.service.ReminderService;
import com.server.server.utils.LoggerUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping("/reminder")
public class ReminderController {

    private static final LoggerUtils LOG = new LoggerUtils(ReminderController.class);

    private ResponseEntity mResponse;

    @Autowired
    private ReminderService mService;

    @RequestMapping(value = "/get", method = GET)
    @ResponseBody
    public List<Remind> getAllReminders() {
        LOG.info("getAllReminds");
        return mService.getAll();
    }

    @RequestMapping(value = "/get-by-id", params = "id", method = GET)
    @ResponseBody
    public Remind getReminder(@RequestParam(value = "id") long remindID) {
        LOG.info("getById: id = " + remindID);
        return mService.getByID(remindID);
    }

    @RequestMapping(value = "/get-by-type", params = "type", method = GET)
    @ResponseBody
    public List<Remind> getListByType(@RequestParam(value = "type") String typeRemind) {
        LOG.info("getListByType: type = " + typeRemind);
        return mService.getListByType(typeRemind);
    }

    @RequestMapping(value = "/create", method = POST)
    @ResponseBody
    public ResponseEntity createReminder(@RequestBody Remind remind) {
        LOG.info("createReminder: " + remind.toString());
//        Remind newRemind = mService.isRemindExists(remind.getTitle(), remind.getDescription());
//        if (newRemind != null) {
        mService.create(remind);
        return mResponse = ResponseEntity.status(HttpStatus.OK).body("Successful create remind.");
//        } else {
//            return mResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body("This remind already exists!");
//        }
    }

    @RequestMapping(value = "/delete", method = POST)
    @ResponseBody
    public ResponseEntity delete(@RequestBody Remind remind) {
        LOG.info("delete: " + remind.toString());
        mService.delete(remind);
        return mResponse = ResponseEntity.status(HttpStatus.ACCEPTED).body("Successful delete.");
    }

    // @PathVariable == @Path
//    @RequestMapping(value = "/delete/{id}", method = DELETE)
//    @ResponseBody
//    public void delete(@PathVariable long id) {
//        mService.delete(id);
//    }
}