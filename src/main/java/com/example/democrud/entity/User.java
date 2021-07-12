package com.example.democrud.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@Entity
@Table(name="User")
public class User {

    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String phone;
    private String email;
    private int age;


}
