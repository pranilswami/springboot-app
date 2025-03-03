package com.app.service.evaluation;

import com.app.config.TwilioConfig;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;

public class SmsService {
    private final TwilioConfig twilioConfig;

    @Autowired
    public SmsService(TwilioConfig twilioConfig) {
        this.twilioConfig = twilioConfig;
    }

    public void sendSms(String to,String body){
        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber("+18152056837"),
                body
                ).create();
    }
}
