package com.example.machado.myapplication1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class AdaptadorBaseDados {
    private AjudaUsoBaseDados dbHelper;
    private SQLiteDatabase database;
    public AdaptadorBaseDados(Context context) {
        dbHelper = new AjudaUsoBaseDados(context.getApplicationContext());
    }
    public AdaptadorBaseDados open() {
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close() {
        dbHelper.close();
    }

    public void inserirUser(String nome, String password) {
        ContentValues values = new ContentValues() ;
        values.put("username", nome);
        values.put("password", password);
        database.insert("users", null, values);
    }

    public void dropUsers(){
        database.execSQL("DROP TABLE IF EXISTS users");
        database.execSQL("CREATE TABLE users(_id integer primary key autoincrement, username varchar(40) unique, password varchar(40))");

    }

    private Cursor obterTodosRegistos() {
        String[] colunas = new String[5];
        colunas[0] = "_id";
        colunas[1] = "username";
        colunas[2] = "password";
        return database.query("users", colunas, null, null, null, null, "_id");
    }

    public void updatePassword(String _username, String _newpassword) {
        String whereClause = "username = ?";
        String[] whereArgs = new String[1];
        whereArgs[0] = _username;
        ContentValues values = new ContentValues();
        values.put("password", _newpassword);
        database.update("users", values, whereClause, whereArgs);
    }

    public int getSize() {
        return (int)DatabaseUtils.queryNumEntries(database, "users");
    }

    public String getPasswordFromUser(String _username) {
        String password;
        String whereClause = "username = ?";
        String whereArgs[] = new String[1];
        whereArgs[0] = _username;
        Cursor cursor = database.query("users", null, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();

        password = cursor.getString(2);
        cursor.close();

        return password;
    }


    public boolean checkUsernameExists(String _username){
        boolean exists = false;
        String whereClause = "username = ?";
        String whereArgs[] = new String[1];
        whereArgs[0] = _username + "";
        Cursor cursor = database.query("users", null, whereClause, whereArgs, null, null, null);
        cursor.moveToFirst();

        if(cursor.getCount() != 0){
            exists = true;
        }
        else{
            exists = false;
        }

        cursor.close();

        return exists;
    }


}