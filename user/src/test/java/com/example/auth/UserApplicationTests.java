package com.example.auth;

import com.example.auth.user.domain.entity.User;
import com.example.auth.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserApplicationTests {
	@Autowired
	UserRepository userRepository;
	@Test
	void contextLoads() {
		List<User> all = userRepository.findAll();
		all.stream().forEach(System.out::println);
		System.out.println();
	}

}
