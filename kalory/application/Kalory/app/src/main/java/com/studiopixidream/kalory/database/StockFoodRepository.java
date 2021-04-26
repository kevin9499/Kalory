package com.studiopixidream.kalory.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.studiopixidream.kalory.model.Day;
import com.studiopixidream.kalory.model.Food;
import com.studiopixidream.kalory.model.StockFood;

import java.util.ArrayList;

public class StockFoodRepository implements IStockFoodRepository {
    private DatabaseManager dbm;
    private static StockFoodRepository instance;

    private StockFoodRepository(Context context) {
        this.dbm = DatabaseManager.getInstance(context);
    }

    public static StockFoodRepository getInstance(Context context) {
        if (instance == null) {
            instance = new StockFoodRepository(context);
        }
        return instance;
    }

    @Override
    public boolean add(StockFood f) {
        ContentValues values = new ContentValues();
        values.put("id", f.getId());
        values.put("name", f.getName());
        values.put("kcal", f.getKcal());
        values.put("meal", f.getMeal());
        values.put("dayId", f.getDayId());
        String[] identifier = {String.valueOf(f.getDayId())};
        long line = dbm.getWritableDatabase().insert("kalory_food", null, values);
        Cursor c = dbm.getReadableDatabase().rawQuery("select sum(kcal) from kalory_food where dayId=?", identifier);
        c.moveToFirst();
        ContentValues values2 = new ContentValues();
        values2.put("Tkcal", c.getInt(0));
        long line3= dbm.getWritableDatabase().update("kalory_day", values2,"id=?", identifier);
        c.close();
        return line != 0 && line3 !=0;
    }

    @Override
    public StockFood getFood() {
        StockFood f = new StockFood();
        Cursor c = dbm.getReadableDatabase().rawQuery("select * from kalory_food", null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            f.setId(c.getInt(0));
            f.setName(c.getString(1));
            f.setKcal(c.getInt(2));
            f.setMeal(c.getString(3));
            f.setDayId(c.getString(4));
        }
        c.close();
        return f;
    }

    @Override
    public ArrayList<StockFood> getAll() {
        ArrayList<StockFood> foods = new ArrayList<>();
        Cursor c = dbm.getReadableDatabase().rawQuery("select * from kalory_food", null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            while (!c.isAfterLast()) {
                StockFood f = new StockFood();
                f.setId(c.getInt(0));
                f.setName(c.getString(1));
                f.setKcal(c.getInt(2));
                f.setMeal(c.getString(3));
                f.setDayId(c.getString(4));
                foods.add(f);
                c.moveToNext();
            }
        }
        c.close();
        return foods;
    }
    @Override
    public ArrayList<StockFood> getDayFood(Day d) {
        ArrayList<StockFood> foods = new ArrayList<>();
        if(d != null) {
            String[] identifier = {String.valueOf(d.getId())};
            Cursor c = dbm.getReadableDatabase().rawQuery("select * from kalory_food where dayId=?", identifier);
            c.moveToFirst();
            if (c.getCount() > 0) {
                while (!c.isAfterLast()) {
                    StockFood f = new StockFood();
                    f.setId(c.getInt(0));
                    f.setName(c.getString(1));
                    f.setKcal(c.getInt(2));
                    f.setMeal(c.getString(3));
                    f.setDayId(c.getString(4));
                    foods.add(f);
                    c.moveToNext();
                }
            }
            c.close();
        }
        return foods;
    }
}
