package com.example.tosspay.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;


import java.util.Base64;

public class HttpHeaderUtil {

    public static HttpHeaders createHeaders(String secretKey) {
        String credentials = secretKey + ":";
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Basic " + encodedCredentials);
        headers.setContentType(MediaType.APPLICATION_JSON);

        return headers;
    }
}
