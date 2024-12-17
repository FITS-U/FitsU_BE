package com.example.card;

import com.example.card.domain.CardInfo;
import com.example.card.repository.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
class CardApplicationTests {

	@Autowired
	private CardRepository cardRepository;
	@Autowired
	private CardImageRepository cardImageRepository;

	@Test
	@Transactional
	@Rollback(false)
	void contextLoads() {

		List<CardInfo> cards = cardRepository.findAll();
		for (CardInfo card : cards) {
			List<CardImage> cardImages = cardImageRepository.findByCardId(card.getCardId());
			String imageUrl = cardImages.isEmpty() ? null : cardImages.get(0).getImageURL();
			card.setImageUrl(imageUrl);
			cardRepository.save(card);
		}
	}

}
