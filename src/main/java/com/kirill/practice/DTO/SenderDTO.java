package com.kirill.practice.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SenderDTO {
    int id;
    String firstName;
    String middleName;
    String lastName;
    String accountNumber;
    BankDTO bank;
}