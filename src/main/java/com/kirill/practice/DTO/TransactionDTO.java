package com.kirill.practice.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionDTO {
    int id;
    String product;
    String subProduct;
    SenderDTO sender;
    double summa;
    String transferDate;
    CurrencyDTO currency;
    RecipientDTO recipient;
    String payToolType;
    String payToolName;
    String status;
}