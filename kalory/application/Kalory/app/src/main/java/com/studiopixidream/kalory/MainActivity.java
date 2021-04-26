package com.studiopixidream.kalory;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import com.studiopixidream.kalory.database.UserRepository;
import com.studiopixidream.kalory.fragment.DayFragment;
import com.studiopixidream.kalory.fragment.FoodFragment;
import com.studiopixidream.kalory.fragment.MenuFragment;
import com.studiopixidream.kalory.fragment.WeekFragment;
import com.studiopixidream.kalory.fragment.NewUserFragment;
import com.studiopixidream.kalory.model.Day;
import com.studiopixidream.kalory.model.Food;
import com.studiopixidream.kalory.model.IListenerSearchFood;
import com.studiopixidream.kalory.model.IOnAddFood;
import com.studiopixidream.kalory.model.IOnAddWeek;
import com.studiopixidream.kalory.model.IOnContinue;
import com.studiopixidream.kalory.model.IOnSelectDay;
import com.studiopixidream.kalory.model.Week;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements IOnContinue, IOnAddWeek, IOnAddFood, IOnSelectDay, IListenerSearchFood {

    private NewUserFragment newUserFragment;
    private MenuFragment menuFragment;
    private FoodFragment foodFragment;
    private DayFragment dayFragment;
    private WeekFragment weekFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blue)));

        setContentView(R.layout.main_activity);
        newUserFragment = new NewUserFragment();
        menuFragment = new MenuFragment();
        dayFragment = new DayFragment();
        foodFragment = new FoodFragment();
        weekFragment = new WeekFragment();

        if (!UserRepository.getInstance(this).isRegistered()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentLayout, newUserFragment)
                    .add(R.id.fragmentLayout, menuFragment)
                    .add(R.id.fragmentLayout, dayFragment)
                    .add(R.id.fragmentLayout, foodFragment)
                    .add(R.id.fragmentLayout, weekFragment)
                    .hide(menuFragment)
                    .hide(dayFragment)
                    .hide(weekFragment)
                    .hide(foodFragment)
                    .commit();
            newUserFragment.setListener(this);
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragmentLayout, newUserFragment)
                    .add(R.id.fragmentLayout, menuFragment)
                    .add(R.id.fragmentLayout, dayFragment)
                    .add(R.id.fragmentLayout, foodFragment)
                    .add(R.id.fragmentLayout, weekFragment)
                    .hide(dayFragment)
                    .hide(foodFragment)
                    .hide(weekFragment)
                    .hide(newUserFragment)
                    .commit();
            menuFragment.setListener(this);
        }
    }

    @Override
    public void onBackPressed() {
        if (menuFragment.isVisible() || newUserFragment.isVisible()) {
            super.onBackPressed();
        } else if (weekFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(weekFragment)
                    .show(menuFragment)
                    .commit();
        } else if (dayFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(dayFragment)
                    .show(weekFragment)
                    .commit();
        } else if (foodFragment.isVisible()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(foodFragment)
                    .show(dayFragment)
                    .commit();
        }
    }

    @Override
    public void onContinue() {

        getSupportFragmentManager().beginTransaction()
                .hide(newUserFragment)
                .show(menuFragment)
                .commit();
        menuFragment.setListener(this);
        menuFragment.refresh();
    }

    @Override
    public void onAddWeek(Week week) {

        weekFragment.setCurrentweek(week);
        getSupportFragmentManager().beginTransaction()
                .hide(menuFragment)
                .show(weekFragment)
                .commit();
        weekFragment.setListener(this);
        menuFragment.refresh();
        weekFragment.refresh();
    }

    @Override
    public void onSelectDay(String weekday, Day day) {

        dayFragment.setCurrentDay(day);
        getSupportFragmentManager().beginTransaction()
                .hide(weekFragment)
                .show(dayFragment)
                .commit();
        dayFragment.setListener(this);
        weekFragment.refresh();
        dayFragment.refresh();
    }

    @Override
    public void onAddFood(Day day) {
        System.out.println(day);

        foodFragment.setCurrentDay(day);
        getSupportFragmentManager().beginTransaction()
                .hide(dayFragment)
                .show(foodFragment)
                .commit();
        foodFragment.setListener(this);
    }

    @Override
    public void onReceiveFoods(ArrayList<Food> foods) {

        getSupportFragmentManager().beginTransaction()
                .hide(foodFragment)
                .show(dayFragment)
                .commit();
        dayFragment.setListener(this);
        weekFragment.refresh();
        dayFragment.refresh();
    }
}