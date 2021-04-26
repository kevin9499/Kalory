package com.studiopixidream.kalory.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.studiopixidream.kalory.R;
import com.studiopixidream.kalory.database.StockFoodRepository;
import com.studiopixidream.kalory.database.WeekRepository;
import com.studiopixidream.kalory.model.Day;
import com.studiopixidream.kalory.model.FoodAdapter;
import com.studiopixidream.kalory.model.IOnAddFood;
import com.studiopixidream.kalory.model.MealAdapter;
import com.studiopixidream.kalory.model.Week;
import com.studiopixidream.kalory.model.StockFood;
import com.studiopixidream.kalory.model.WeekAdapter;


import java.util.ArrayList;

public class DayFragment extends Fragment implements View.OnClickListener {

    private TextView textViewDay;
    private Button buttonAdd;
    private IOnAddFood listener;
    private ListView listViewMeal;
    private Day currentDay;

    MealAdapter mealAdapter;


    private ArrayList<StockFood> foods = new ArrayList<StockFood>();
    public void setCurrentDay(Day currDay) {
        currentDay = currDay;
    }

    public void setListener(IOnAddFood listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.day_fragment, null);

        textViewDay = v.findViewById(R.id.textViewDay);
        listViewMeal = v.findViewById(R.id.listViewMeal);
        refresh();
        buttonAdd = v.findViewById(R.id.buttonAdd);
        buttonAdd.setOnClickListener(this);

        return v;
    }

    @Override
    public void onClick(View v) {
        listener.onAddFood(currentDay);
    }

    @Override
    public void onResume() {
        super.onResume();
        refresh();

    }

    public void refresh() {
        foods = StockFoodRepository.getInstance(getActivity()).getDayFood(currentDay);
        handleAdapterChange();
        handleChangeView();
    }
    private void handleChangeView() {
        if (foods.size() <= 0) {
            this.listViewMeal.setVisibility(View.GONE);
        } else {
            this.listViewMeal.setVisibility(View.VISIBLE);
        }
    }

    private void handleAdapterChange() {
        if (foods.size() > 0) {
            mealAdapter = new MealAdapter(getActivity(), foods);
            listViewMeal.setAdapter(mealAdapter);
        } else {
            mealAdapter = new MealAdapter(getActivity(), foods);
            listViewMeal.setAdapter(mealAdapter);
        }
    }
}