package com.example.simpleadaptertest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.arrayadaptertest.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView lv_subjects;
    ArrayList<HashMap<String, String>> datas;
    SimpleAdapter simpleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_subjects = findViewById(R.id.lv_subjects);
        datas = new ArrayList<HashMap<String, String>();
        HashMap<String, String> map = new HashMap<>();

        map.put("name", "모바일 프로그래밍");
        map.put("opinison", "하기 싫다");
        datas.add(map);

        map.put("name", "유주경이오");
        map.put("opinison", "하기 싫다");
        datas.add(map);

        simpleAdapter = new SimpleAdapter(this,datas,android.R.layout.simple_list_item_2,
                new String[]{"name", "optinion"},
                new int[]{android.R.id.text1, android.R.id.text2});
        lv_subjects.setAdapter(simpleAdapter);

        lv_subjects.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText((MainActivity.this, datas.get(position).get("name") + "," +
                        datas.get(position).get("opinion"), Toast.LENGTH_SHORT).show();
            }
        });


    }
}