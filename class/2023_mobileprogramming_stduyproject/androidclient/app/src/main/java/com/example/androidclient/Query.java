package com.example.androidclient;

import java.io.Serializable;
import java.util.ArrayList;

public class Query implements Serializable {
    public String header;
    ArrayList<User> users;

    public Query(String header, ArrayList<User> users) {
        this.header = header;
        this.users = users;
    }
}