package com.training.user.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
public class User {

    @Id
    @GeneratedValue
    private Long id;
    private String surname;
    private String lastname;
    private int age;
    private String email;
    private String address;

    public User(String surname, String lastname, int age, String email, String address) {
        this.surname = surname;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
        this.address = address;
    }
}
