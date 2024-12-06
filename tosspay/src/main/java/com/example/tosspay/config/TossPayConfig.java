package com.example.tosspay.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "payment.toss")
@Getter
@Setter
public class TossPayConfig {
    private String testClientApiKey;
    private String testSecretApiKey;
    private String apiUrl;

}
