package com.kirill.practice.entity;

import jakarta.persistence.*;
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
@Table(name = "sender")
public class Sender implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
                ", \"bank\": " + bank +
                '}';
    }
}