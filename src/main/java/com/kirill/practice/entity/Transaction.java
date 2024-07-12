package com.kirill.practice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Entity
@Builder
@Table(name = "transaction")
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    private String product;
    private String subProduct;
    private Sender sender;
    private double summa;
    private String transferDate;
    private Currency currency;
    private Recipient recipient;
    private String payToolType;
    private String payToolName;
    private String status;

    @Override
    public String toString() {
        return "{" +
                "\"ID\": " + ID +
                ", \"product\": \"" + product + '\"' +
                ", \"subProduct\": \"" + subProduct + '\"' +
                ", \"sender\": " + sender +
                ", \"summa\": " + summa +
                ", \"transferDate\": \"" + transferDate + '\"' +
                ", \"currency\": " + currency +
                ", \"recipient\": " + recipient +
                ", \"payToolType\": \"" + payToolType + '\"' +
                ", \"payToolName\": \"" + payToolName + '\"' +
                ", \"status\": \"" + status + '\"' +
                '}';
    }
}