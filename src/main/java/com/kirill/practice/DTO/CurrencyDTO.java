package com.kirill.practice.DTO;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CurrencyDTO {
    int id;
    String name;
    String code;
    int isoCode;
}