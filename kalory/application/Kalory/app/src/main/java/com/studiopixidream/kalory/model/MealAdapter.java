package com.studiopixidream.kalory.model;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.studiopixidream.kalory.R;
import com.studiopixidream.kalory.fragment.DayFragment;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MealAdapter extends BaseAdapter {

    private ArrayList<StockFood> foods;
    private Activity activity;

    public MealAdapter (Activity activity, ArrayList<StockFood> foods) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(activity).inflate(R.layout.food_list, parent, false);
        }
        TextView textViewItemName = convertView.findViewById(R.id.textViewFoodName);
        textViewItemName.setText(foods.get(position).getName());
        TextView textViewItemName2 = convertView.findViewById(R.id.textViewTkcal);
        textViewItemName2.setText(String.valueOf(foods.get(position).getKcal()));
        TextView textViewItemName3 = convertView.findViewById(R.id.textViewMeal);
        textViewItemName3.setText(foods.get(position).getMeal());
        return convertView;
    }
}
