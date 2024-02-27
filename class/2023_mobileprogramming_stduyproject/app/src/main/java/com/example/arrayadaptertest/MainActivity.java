package com.example.arrayadaptertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv_students;
    ArrayList<String> students;
    ArrayAdapter arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_students = findViewById(R.id.lv_students);
        students = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, students);
        lv_students.setAdapter(arrayAdapter);

        students.add("Faker");
        students.add("Faker 2");
        students.add("Faker3");
        students.add("Faker4");
        students.add("Faker5");
        students.add("Faker6");
        students.add("Faker");
        students.add("Faker");
        students.add("Faker");
        students.add("Faker");
        students.add("Faker");
        students.add("Faker");
        students.add("Faker");
        students.add("Faker");
        students.add("Faker");
        students.add("Faker");
        students.add("Faker");
        students.add("Faker");


        lv_students.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, students.get(position) + "," + id, Toast.LENGTH_SHORT).show();
            }
        });
    }
}