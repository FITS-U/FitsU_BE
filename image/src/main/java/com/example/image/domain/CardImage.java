package com.example.image.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "cardImage")
public class CardImage {
    @Id
    private String id;
    private String ImageURL;
    private Long cardId;
}
