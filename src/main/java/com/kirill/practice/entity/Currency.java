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
@Table(name = "currency")
public class Currency implements Serializable {
    @Id
    @Column(unique = true)
    private int id;
    private String name;
    @Column(unique = true)
    private String code;
    @Column(unique = true)
    private int isoCode;

    @Override
    public String toString() {
        return "{" +
                "\"id\": " + id +
                ", \"name\": \"" + name + '\"' +
                ", \"code\": \"" + code + '\"' +
                ", \"isoCode\": " + isoCode +
                '}';
    }
}