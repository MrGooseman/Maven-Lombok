package com.example.demo;

import jakarta.persistence.*;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Persons")
@Getter
@Setter
@NoArgsConstructor
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = true)
    private int age;

    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String city;

    @Column(nullable = true)
    private LocalDateTime registrationDateTime;

    public PersonEntity(String userName, int age, String email, String city, LocalDateTime registrationDateTime) {
        this.userName = userName;
        this.age = age;
        this.email = email;
        this.city = city;
        this.registrationDateTime = registrationDateTime;
    }
}
