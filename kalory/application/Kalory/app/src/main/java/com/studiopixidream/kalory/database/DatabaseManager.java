package com.studiopixidream.kalory.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "mydb.sqlite";
    private static final int CURRENT_DB_VERSION = 1;
    private static DatabaseManager instance;

    public static DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    private DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, CURRENT_DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table kalory_user " +
                "(id INTEGER, name TEXT,age TEXT, height TEXT, weight TEXT," +
                "gender TEXT);");
        db.execSQL("create table kalory_week " +
                "(id INTEGER, name TEXT);");
        db.execSQL("create table kalory_day " +
                "(id INTEGER, name TEXT, Tkcal INTEGER, weekId INTEGER);");
        db.execSQL("create table kalory_food " +
                "(id INTEGER, name TEXT, kcal INTEGER, meal TEXT, dayId INTEGER);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
            case 2:
            case 3:
            default:
        }
    }
}
