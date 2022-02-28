package com.expensetracker.notification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NotificationController {

    @PutMapping("/settings")
    public ResponseEntity<?> saveNotificationSettings(){
        return null;
    }
}
