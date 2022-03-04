package com.expensetracker.notification.service;

import com.expensetracker.notification.model.Recipient;

import java.util.List;

public interface RecipientService {

    List<Recipient> getAllRecipientToNotify();
}
