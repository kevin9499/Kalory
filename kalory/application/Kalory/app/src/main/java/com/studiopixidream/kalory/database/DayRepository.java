package com.studiopixidream.kalory.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.studiopixidream.kalory.model.Day;
import com.studiopixidream.kalory.model.Food;

import java.util.ArrayList;

public class DayRepository implements IDayRepository {

    private DatabaseManager dbm;
    private static DayRepository instance;

    private DayRepository(Context context) {
        this.dbm = DatabaseManager.getInstance(context);
    }

    public static DayRepository getInstance(Context context) {
        if (instance == null) {
            instance = new DayRepository(context);
        }
        return instance;
    }

    @Override
    public boolean add(Day d) {
        ContentValues values = new ContentValues();
        values.put("id", d.getId());
        values.put("name", d.getName());
        values.put("Tkcal", d.getTkcal());
        values.put("weekId", d.getWeekid());
        long line = dbm.getWritableDatabase().insert("kalory_day", null, values);
        return line != 0;
    }

    @Override
    public boolean isDayExist(String weekday, int weekId) {
        String[] identifier = {weekday, String.valueOf(weekId)};
        Cursor c = dbm.getReadableDatabase().rawQuery("select * from kalory_day where name=? and weekId=?", identifier);
        return c.getCount() > 0;

    }

    @Override
    public Day getDay(String dayname) {
        Day d = new Day();
        String[] identifier = {dayname};
        Cursor c = dbm.getReadableDatabase().rawQuery("select * from kalory_day where name=?", identifier);
        c.moveToFirst();
        if (c.getCount() > 0) {
            d.setId(c.getInt(0));
            d.setName(c.getString(1));
            d.setTkcal(c.getString(2));
            d.setWeekid(c.getInt(3));
        }
        c.close();
        return d;
    }

    @Override
    public ArrayList<Day> getAll() {
        ArrayList<Day> days = new ArrayList<>();
        Cursor c = dbm.getReadableDatabase().rawQuery("select * from kalory_day", null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            while (!c.isAfterLast()) {
                Day d = new Day();
                d.setId(c.getInt(0));
                d.setName(c.getString(1));
                d.setTkcal(c.getString(2));
                d.setWeekid(c.getInt(3));
                days.add(d);
                c.moveToNext();
            }
        }
        c.close();
        return days;
    }
}
