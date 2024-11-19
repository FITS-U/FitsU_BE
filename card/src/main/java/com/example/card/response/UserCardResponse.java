package com.example.card.response;

import com.example.card.domain.UserCard;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record UserCardResponse(
        LocalDate expDate,
        Long cardId,
        Long accountId
) {
    public static UserCardResponse from(UserCard userCard) {
        return new UserCardResponse(userCard.getExpDate(), userCard.getCardInfo().getCardId(), userCard.getAccountId());
    }
}
