package com.example.androidclient;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    EditText et_name, et_addr, et_email, et_age;
    Button btn_addUser, btn_printDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = findViewById(R.id.et_name);
        et_addr = findViewById(R.id.et_addr);
        et_email = findViewById(R.id.et_email);
        et_age = findViewById(R.id.et_age);
        btn_addUser = findViewById(R.id.btn_addUser);
        btn_printDB = findViewById(R.id.btn_printDB);
        btn_addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String name = et_name.getText().toString();
                        String address = et_addr.getText().toString();
                        String email = et_email.getText().toString();
                        int age = Integer.parseInt(et_age.getText().toString());
                        User user = new User(name, address, email, age);
                        ArrayList<User> users = new ArrayList<User>();
                        users.add(user);
                        Query query = new Query("add", users);
                        send(query);
                    }
                }).start();
            }
        });
        btn_printDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Query query = new Query("checkAll", null);
                        send(query);
                    }
                }).start();
            }
        });
    }
    public void send(Query query){
        int portNumber = 5000;
        try {
            Socket sock = new Socket("10.0.2.2", portNumber);
            ObjectOutputStream outStream = new ObjectOutputStream(sock.getOutputStream());
            outStream.writeObject(query);
            outStream.flush();
            ObjectInputStream inStream = new ObjectInputStream(sock.getInputStream());
            Intent intent = new Intent(MainActivity.this, DBActivity.class);
            if(query.header.equals("add")){
                String responseStr = "" + inStream.readObject();
                intent.putExtra("STRING", responseStr);
                startActivity(intent);
            }
            else if(query.header.equals("checkAll")){
                Query responseQuery = (Query) inStream.readObject();
                ArrayList<User> users = responseQuery.users;
                intent.putExtra("USERS", users);
                startActivity(intent);
            }
            sock.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}