package com.example.bankImage.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "bankImage")
public class BankImage {
    @Id
    private String id;
    private String bankImageUrl;
    private Long bankId;
}
