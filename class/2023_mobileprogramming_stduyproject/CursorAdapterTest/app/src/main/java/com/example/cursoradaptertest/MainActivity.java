package com.example.cursoradaptertest;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    ListView lv_movies;
    ArrayList<HashMap<String, String>> cursorDatas;
    CursorAdapter cursorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv_movies = findViewById(R.id.lv_movies);
        cursorDatas = new ArrayList<HashMap<String, String>>();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from tb_data", null);

        while(cursor.moveToNext()){
            HashMap<String, String> map = new HashMap<>();
            map.put("title", cursor.getString(1));
            map.put("content", cursor.getString(2));
            cursorDatas.add(map);
        }
        cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor,
                new String[]{"title", "content"},
                new int[]{android.R.id.text1, android.R.id.text2},
                CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        lv_movies.setAdapter(cursorAdapter);

        lv_movies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, cursorDatas.get(position).get("title") + "," +
                cursorDatas.get(position).get("content"), Toast.LENGTH_SHORT).show();
            }
        });
    }
}