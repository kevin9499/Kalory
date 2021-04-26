package com.studiopixidream.kalory.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.studiopixidream.kalory.model.User;

public class UserRepository implements IUserRepository {

    private DatabaseManager dbm;
    private static UserRepository instance;

    private UserRepository(Context context) {
        this.dbm = DatabaseManager.getInstance(context);
    }

    public static UserRepository getInstance(Context context) {
        if (instance == null) {
            instance = new UserRepository(context);
        }
        return instance;
    }

    @Override
    public boolean add(User u) {
        ContentValues values = new ContentValues();
        values.put("id", u.getId());
        values.put("name", u.getName());
        values.put("age", u.getAge());
        values.put("height", u.getHeight());
        values.put("weight", u.getWeight());
        values.put("gender", u.getGender());
        long line = dbm.getWritableDatabase().insert("kalory_user", null, values);
        return line != 0;
    }

    @Override
    public boolean isRegistered() {
        Cursor c = dbm.getReadableDatabase().rawQuery("select * from kalory_user", null);
        return c.getCount() > 0;
    }

    @Override
    public User getUser() {
        User u = new User();
        Cursor c = dbm.getReadableDatabase().rawQuery("select * from kalory_user", null);
        c.moveToFirst();
        if (c.getCount() > 0) {
            u.setId(c.getInt(0));
            u.setName(c.getString(1));
            u.setAge(c.getString(2));
            u.setHeight(c.getString(3));
            u.setWeight(c.getString(4));
            u.setGender(c.getString(5));
        }
        c.close();
        return u;
    }
}
