package com.kirill.practice.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankDTO {
    int id;
    String name;
    long bic;
    String inn;
    String accountNumber;
}