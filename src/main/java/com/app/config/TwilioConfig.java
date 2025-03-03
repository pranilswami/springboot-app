package com.app.config;

import com.app.service.evaluation.SmsService;
import com.twilio.Twilio;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TwilioConfig {
    @Value("${twilio.account.sid}")
    private String accountSid;
    @Value("${twilio.auth.token}")
    private String authToken;
    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Bean
    public void init() {
        Twilio.init(accountSid, authToken);
        System.out.println("Twilio initialized successfully");
    }

    public String getTwilioPhoneNumber(){
        return twilioPhoneNumber;
    }
    @Bean
    public SmsService smsService() {
        return new SmsService(new TwilioConfig());
    }
}
