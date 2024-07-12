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
@Table(name = "bank")
public class Bank implements Serializable {
    @Id
    @Column(unique = true)
    private int id;
    private String name;
    @Column(unique = true)
    private long bic;
    @Column(unique = true)
    private String inn;
    @Column(unique = true)
    private String accountNumber;

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"name\": \"" + name + '\"' +
                ", \"bic\": " + bic +
                ", \"inn\": \"" + inn + '\"' +
                ", \"accountNumber\": \"" + accountNumber + '\"' +
                '}';
    }
}