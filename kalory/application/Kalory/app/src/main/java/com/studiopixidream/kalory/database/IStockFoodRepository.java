package com.studiopixidream.kalory.database;

import com.studiopixidream.kalory.model.Day;
import com.studiopixidream.kalory.model.StockFood;

import java.util.ArrayList;

public interface IStockFoodRepository {
    public boolean add(StockFood f);

    public StockFood getFood();

    public ArrayList<StockFood> getAll();

    public ArrayList<StockFood> getDayFood(Day d);
}
