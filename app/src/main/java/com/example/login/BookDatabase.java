package com.example.login;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BookDatabase   extends SQLiteOpenHelper {
    private static final String NAME = "books.DB";
    private static final int VERSION = 1;
    public static final String TABLE_NAME = "books";
    public BookDatabase(@Nullable Context context) {
        super(context, NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table books(name TEXT primary key, author TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS books");
        onCreate(db);
    }

    public Boolean checkName(String name) {
        SQLiteDatabase database = this.getWritableDatabase();
        Cursor cursor = database.rawQuery("Select * from books where name = ?", new String[]{name});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean insertData(String name, String author) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("author", author);
        long result = database.insert("books", null, contentValues);
        if (result == -1) return false;
        else return true;
    }
}
