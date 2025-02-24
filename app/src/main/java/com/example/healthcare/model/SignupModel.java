package com.example.healthcare.model;

public class SignupModel {
    public String id, name, email, adress, age,password;

    public SignupModel(String id, String name, String email, String adress, String age, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.adress = adress;
        this.age = age;
        this.password = password;
    }
}
