package com.example.androidclient;

import java.io.Serializable;

public class User implements Serializable {
    public String name;
    public String address;
    public String email;
    public int age;

    public User(String name, String address, String email, int age) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }
}