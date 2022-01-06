package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database  extends SQLiteOpenHelper {
    private static final String NAME = "Signup.DB";
    private static final int VERSION = 1;
    public static final String TABLE_NAME = "users";
    public Database(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table users(username TEXT primary key, password TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS Currency");
        onCreate(db);
    }

    public Boolean checkUsername(String user) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from users where username = ?", new String[]{user});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkUsernamePassword(String user, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from users where username = ? and password = ?", new String[]{user, password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean insertData(String user, String password) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", user);
        contentValues.put("password", password);
        long result = database.insert("users", null, contentValues);
        if (result == -1) return false;
        else return true;
    }
}
