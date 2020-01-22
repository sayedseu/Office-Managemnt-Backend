package com.example.oficemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserToken {
    @Id
    private String id;
    private String name;
    private String email;
    private String phone;
    private String password;
}
