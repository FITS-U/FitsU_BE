package com.example.tosspay;

import com.example.tosspay.config.TossPayConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TossPayConfig.class)
public class TosspayApplication {

	public static void main(String[] args) {
		SpringApplication.run(TosspayApplication.class, args);
	}

}
