package com.studiopixidream.kalory.service;

import android.content.Context;

import com.studiopixidream.kalory.model.Food;
import com.studiopixidream.kalory.model.IListenerSearchFood;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Objects;

public class ServiceSearchFood {
    private static String readJson(Context ctx) {
        String json = null;
        try {
            InputStream is = ctx.getAssets().open("products.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static Food searchFood(Context ctx, String search) throws JSONException {
        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(readJson(ctx)));
        JSONArray jsonArray = jsonObject.getJSONArray("products");
        Food tmp = new Food();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            if (object.getString("name").equals(search)) {
                tmp.setId(object.getInt("id"));
                tmp.setName(object.getString("name"));
                tmp.setCal(object.getInt("cal"));
                tmp.setTag(object.getString("tag"));
            }
        }
        return tmp;
    }

    public static void getFood(Context ctx, IListenerSearchFood listener) throws JSONException {
        JSONObject jsonObject = new JSONObject(Objects.requireNonNull(readJson(ctx)));
        JSONArray jsonArray = jsonObject.getJSONArray("products");
        ArrayList<Food> foods = new ArrayList<Food>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            Food tmp = new Food();
            tmp.setId(object.getInt("id"));
            tmp.setName(object.getString("name"));
            tmp.setCal(object.getInt("cal"));
            tmp.setTag(object.getString("tag"));
            foods.add(tmp);
        }
        listener.onReceiveFoods(foods);
    }
}