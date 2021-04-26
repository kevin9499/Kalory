package com.studiopixidream.kalory.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studiopixidream.kalory.R;

import java.util.ArrayList;

public class FoodAdapter extends BaseAdapter {
    private ArrayList<Food> foods;
    private Activity activity;

    public FoodAdapter (Activity activity, ArrayList<Food> foods) {
        this.foods = foods;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return foods.size();
    }

    @Override
    public Object getItem(int position) {
        return foods.get(position);
    }

    @Override
    public long getItemId(int position) {
        return foods.get(position).getId();
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        if (converView == null) {
            converView = LayoutInflater.from(activity).inflate(R.layout.item_list, parent, false);
        }
        TextView textViewItemName = converView.findViewById(R.id.textViewItemName);
        textViewItemName.setText(foods.get(position).getName());

        return converView;
    }
}
