package com.studiopixidream.kalory.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.studiopixidream.kalory.R;
import com.studiopixidream.kalory.database.IDayRepository;
import com.studiopixidream.kalory.database.StockFoodRepository;
import com.studiopixidream.kalory.model.Day;
import com.studiopixidream.kalory.model.Food;
import com.studiopixidream.kalory.model.FoodAdapter;
import com.studiopixidream.kalory.model.IListenerSearchFood;
import com.studiopixidream.kalory.model.IOnSelectDay;
import com.studiopixidream.kalory.model.StockFood;
import com.studiopixidream.kalory.service.ServiceSearchFood;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FoodFragment extends Fragment implements View.OnClickListener, IListenerSearchFood {
    private Spinner dayOfWeekSpinner;
    private AutoCompleteTextView actv;
    private ArrayList<Food> foods;
    private FoodAdapter foodAdapter;
    private EditText editTextQt;
    private Spinner spinnerPartOfDay;
    private Button submitButton;
    private IListenerSearchFood listener;

    private Day currentDay;

    public void setCurrentDay(Day currDay) {
        currentDay = currDay;
    }

    public void setListener(IListenerSearchFood listener) {
        this.listener = listener;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.food_fragment, null);
        actv = v.findViewById(R.id.autoCompleteProduct);
        editTextQt = v.findViewById(R.id.editTextQt);
        spinnerPartOfDay = v.findViewById(R.id.spinnerPartOfDay);
        submitButton = v.findViewById(R.id.buttonSubmit);

        submitButton.setOnClickListener(this);

        // put data in combobox
        ArrayAdapter adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, getFoodName());
        actv.setAdapter(adapter);
        actv.setThreshold(1);
        // load json
        try {
            ServiceSearchFood.getFood(getContext(), this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public void onClick(View v) {
        String product = actv.getText().toString();
        int quantity = Integer.parseInt(String.valueOf(editTextQt.getText()));
        String partOfDay = spinnerPartOfDay.getSelectedItem().toString();
        Food food = new Food();
        try {
            food = ServiceSearchFood.searchFood(getContext(), product);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        int cals = food.getCal();
        int totalCal = (cals * quantity) / 100;
        StockFood stockFood = new StockFood(food.getId(), product, totalCal, partOfDay, String.valueOf(currentDay.getId()));
        StockFoodRepository.getInstance(getContext()).add(stockFood);
        this.listener.onReceiveFoods(foods);
    }

    private void handleAdapterChange () {
        foodAdapter = new FoodAdapter(getActivity(), foods);
    }

    private List<String> getFoodName () {
        try {
            ServiceSearchFood.getFood(getContext(), this);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        List<String> foodName = new ArrayList<String>();
        for (int i = 0; i < foods.size(); i++) {
            foodName.add(foods.get(i).getName());
        }
        return foodName;
    }

    public void refresh(){

    }

    @Override
    public void onReceiveFoods(ArrayList<Food> foods) {
        this.foods = foods;
        handleAdapterChange();
    }
}
