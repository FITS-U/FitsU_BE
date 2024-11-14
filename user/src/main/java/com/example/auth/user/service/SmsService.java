package com.example.auth.user.service;

import com.example.auth.user.domain.dto.SmsRequest;

public interface SmsService {

    void sendSms(SmsRequest smsRequest);
}
