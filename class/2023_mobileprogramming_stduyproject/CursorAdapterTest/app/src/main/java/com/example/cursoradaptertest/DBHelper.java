package com.example.cursoradaptertest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;

    public DBHelper(@Nullable Context context) {
        super(context, "datadb", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableSQL = "create table tb_data (" +
                "_id integer primary key autoincrement," +
                "title," +
                "content)";
        db.execSQL(tableSQL);
        db.execSQL("insert into tb_data (title, content) values ('어벤져스 엔드게임', '아이 앰 아이언맨')");
        db.execSQL("insert into tb_data (title, content) values ('범죄도시', '진실의 방으로')");
        db.execSQL("insert into tb_data (title, content) values ('스타워즈 3', '암 유어 빠더')");
        db.execSQL("insert into tb_data (title, content) values ('아저씨', '이거 방탄유리아, 멍멍아')");
        db.execSQL("insert into tb_data (title, content) values ('탑건 매버릭', '오늘은 아임다')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table tb_data");
            onCreate(db);
        }
    }
}
