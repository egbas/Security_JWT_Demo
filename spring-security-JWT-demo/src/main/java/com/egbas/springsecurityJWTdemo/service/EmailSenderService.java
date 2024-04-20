package com.egbas.springsecurityJWTdemo.service;

import com.egbas.springsecurityJWTdemo.dto.EmailDetails;

public interface EmailSenderService {

    void sendEmailAlert(EmailDetails emailDetails);
}
