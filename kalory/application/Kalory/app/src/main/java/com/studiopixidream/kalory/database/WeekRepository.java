package com.studiopixidream.kalory.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.studiopixidream.kalory.model.Week;

import java.util.ArrayList;

public class WeekRepository implements IWeekRepository {

    private DatabaseManager dbm;
    private static WeekRepository instance;

    private WeekRepository(Context context) {
        this.dbm = DatabaseManager.getInstance(context);
    }

    public static WeekRepository getInstance(Context context) {
        if (instance == null) {
            instance = new WeekRepository(context);
        }
        return instance;
    }

    @Override
    public boolean add(Week w) {
        ContentValues values = new ContentValues();
        values.put("id", w.getId());
        values.put("name", w.getName());
        long line = dbm.getWritableDatabase().insert("kalory_week", null, values);
        return line != 0;
    }

    @Override
    public ArrayList<Week> getAll() {
        Cursor c = dbm.getReadableDatabase().rawQuery("select * from kalory_week", null);
        ArrayList<Week> weeks = new ArrayList<Week>();
        if (c.moveToFirst()) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Week week = new Week();
                week.setId(c.getInt(c.getColumnIndex("id")));
                week.setName(c.getString(c.getColumnIndex("name")));
                weeks.add(week);
                c.moveToNext();
            }
        }
        return weeks;
    }

    @Override
    public int getTotalWeekCal(int weekId) {

        String[] identifier = {String.valueOf(weekId)};
        Cursor c = dbm.getReadableDatabase().rawQuery("select sum(Tkcal) from kalory_day where weekId=?", identifier);
        c.moveToFirst();
        int i = c.getInt(0);
        c.close();
        return i;

    }

    @Override
    public ArrayList<Week> getAllName() {
        Cursor c = dbm.getReadableDatabase().rawQuery("select name from kalory_week", null);
        ArrayList<Week> weeks = new ArrayList<Week>();
        if (c.moveToFirst()) {
            c.moveToFirst();
            while (!c.isAfterLast()) {
                Week week = new Week();
                week.setName(c.getString(c.getColumnIndex("name")));
                weeks.add(week);
                c.moveToNext();
            }
        }
        return weeks;
    }

}
