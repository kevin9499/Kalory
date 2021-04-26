package com.studiopixidream.kalory.database;

import com.studiopixidream.kalory.model.Day;

import java.util.ArrayList;

public interface IDayRepository {
    public boolean add(Day day);

    public boolean isDayExist(String weekday, int weekId);

    public Day getDay(String dayname);

    public ArrayList<Day> getAll();
}
