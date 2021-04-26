package com.studiopixidream.kalory.model;

import java.io.Serializable;

public class StockFood implements Serializable {
    private int id, kcal;
    private String name, meal, dayId;

    public StockFood(Integer id, String name, int kcal, String meal, String dayId) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
        this.meal = meal;
        this.dayId = dayId;
    }

    public StockFood(String name, int kcal, String meal, String dayId) {
        this.name = name;
        this.kcal = kcal;
        this.meal = meal;
        this.dayId = dayId;
    }

    public StockFood() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getKcal() {
        return kcal;
    }

    public void setKcal(int kcal) {
        this.kcal = kcal;
    }

    public String getMeal() {
        return meal;
    }

    public void setMeal(String meal) {
        this.meal = meal;
    }

    public String getDayId() {
        return dayId;
    }

    public void setDayId(String dayId) {
        this.dayId = dayId;
    }
}
