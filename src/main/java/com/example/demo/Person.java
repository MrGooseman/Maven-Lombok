package com.example.demo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor @Getter@Setter
public class Person
{
    private Long id;
    private String userName;
    private int age;
    private String email;
    private String city;
    private LocalDateTime registrationDateTime;


}
