package com.example.machado.myapplication1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AjudaUsoBaseDados extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "base-dados.db";
    private static final int VERSION = 1;
    public AjudaUsoBaseDados(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String s =
                "CREATE TABLE users(_id integer primary key autoincrement, username varchar(40), password varchar(40))";
        db.execSQL(s);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }
}
