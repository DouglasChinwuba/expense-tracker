package com.expensetracker.notification.controller;

import com.expensetracker.notification.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @Autowired
    AccountService accountService;

    @PutMapping("/setting/{username}/{sendEmail}")
    public ResponseEntity<?> updateNotificationSetting(@PathVariable String username,
                                                      @PathVariable boolean sendEmail){
        accountService.updateSetting(username, sendEmail);
        return new ResponseEntity(HttpStatus.OK);
    }
}
