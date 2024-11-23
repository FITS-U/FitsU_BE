package com.example.auth.user.service;

import com.example.auth.global.SmsUtil;
import com.example.auth.user.domain.dto.SmsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SmsServiceImpl implements SmsService {

    private final SmsUtil smsUtil;
    private final Map<String, String> verificationCache = new ConcurrentHashMap<>();
    private final Map<String, Long> expirationCache = new ConcurrentHashMap<>();
    private static final long CODE_EXPIRATION_TIME = 300_000;

    //의존성 주입
    public SmsServiceImpl(@Autowired SmsUtil smsUtil) {
        this.smsUtil = smsUtil;
    }

    @Override // SmsService 인터페이스 메서드 구현
    public void sendSms(SmsRequest smsRequest) {
        String phoneNum = smsRequest.getPhoneNum(); // SmsRequest 에서 전화번호를 가져온다.
        String certificationCode = Integer.toString((int)(Math.random() * (999999 - 100000 + 1)) + 100000); // 6자리 인증 코드를 랜덤으로 생성

        verificationCache.put(phoneNum, certificationCode);
        expirationCache.put(phoneNum, System.currentTimeMillis() + CODE_EXPIRATION_TIME);

        smsUtil.sendSms(phoneNum, certificationCode); // SMS 인증 유틸리티를 사용하여 SMS 발송
    }

    public boolean verifyCode(String phoneNum, String certificationCode) {
        if (!verificationCache.containsKey(phoneNum)) {return false;}

        String storedCode = verificationCache.get(phoneNum);
        Long expirationTime = expirationCache.get(phoneNum);

        if(storedCode.equals(certificationCode) && expirationTime > System.currentTimeMillis()) {
            verificationCache.remove(phoneNum);
            expirationCache.remove(phoneNum);
            return true;
        }
        return false;
    }
}