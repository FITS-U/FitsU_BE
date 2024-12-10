package com.example.consumption;

import com.example.consumption.domain.Bank;
import com.example.consumption.repository.BankRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class ConsumptionApplicationTests {
	@Autowired
	private BankImageRepository bankImageRepository;

	@Autowired
	private BankRepository bankRepository;


	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() {

		List<Bank> banks = bankRepository.findAll();
		for (Bank bank : banks) {
			List<BankImage> bankImages = bankImageRepository.findByBankId(bank.getBankId());
			String imageUrl = bankImages.isEmpty() ? null : bankImages.get(0).getImageUrl();
			bank.setImageUrl(imageUrl);
			bankRepository.save(bank);
		}
	}

}
