package com.example.androidclient;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DBActivity extends AppCompatActivity {
    TextView tv_printDB;
    Button btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbactivity);

        tv_printDB = findViewById(R.id.tv_printDB);
        btn_back = findViewById(R.id.btn_back);

        Intent intent = getIntent();

        String msg = intent.getStringExtra("STRING");
        if(msg != null) {
            tv_printDB.setText(msg);
        }
        ArrayList<User> users = (ArrayList<User>) intent.getSerializableExtra("USERS");
        if(users != null) {
            for(int i=0; i<users.size(); i++) {
                User user = users.get(i);
                String name = user.name;
                String address = user.address;
                String email = user.email;
                int age = user.age;
                tv_printDB.append(name + " " + address + " "+ email + " " +age + "\n");
            }
        }

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}