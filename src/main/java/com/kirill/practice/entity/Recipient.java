package com.kirill.practice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "recipient")
public class Recipient implements Serializable {
    @Id
    @Column(unique = true)
    private int id;
    private String firstName;
    private String middleName;
    private String lastName;
    @Column(unique = true)
    private String accountNumber;
    private Bank bank;

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"firstName\": \"" + firstName + '\"' +
                ", \"middleName\": \"" + middleName + '\"' +
                ", \"lastName\": \"" + lastName + '\"' +
                ", \"accountNumber\": \"" + accountNumber + '\"' +
                ", \"bank\": " + bank.toString() +
                '}';
    }
}