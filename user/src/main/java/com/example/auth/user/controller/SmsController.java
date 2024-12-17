package com.example.auth.user.controller;

import com.example.auth.user.domain.dto.SmsRequest;
import com.example.auth.user.service.SmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/sms")
public class SmsController {

    private final SmsService smsService;

    public SmsController(@Autowired SmsService smsService){
        this.smsService = smsService;
    }

    @PostMapping("/send")
    public void SendSMS(@RequestBody SmsRequest request){
        smsService.sendSms(request);
    }
}