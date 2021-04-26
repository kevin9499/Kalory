package com.studiopixidream.kalory.database;

import com.studiopixidream.kalory.model.Week;

import java.util.ArrayList;

public interface IWeekRepository {
    public boolean add(Week w);

    public ArrayList<Week> getAll();

    public ArrayList<Week> getAllName();

    public int getTotalWeekCal(int weekId);
}
