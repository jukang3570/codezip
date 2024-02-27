package com.example.sqlitedbsample;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

public class ReadDBActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_dbactivity);
        TextView titleView = findViewById(R.id.read_title);
        TextView contentView = findViewById(R.id.read_content);

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select title, content from tb_memo order by id_ desc limit 1",null);
        while(cursor.moveToNext()) {
            titleView.setText((cursor.getString(0)));
            contentView.setText((cursor.getString(1)));
        }

        db.close();
    }
}