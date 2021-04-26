package com.studiopixidream.kalory.model;

import java.io.Serializable;

public class Food implements Serializable {
    private int id;
    private String name;
    private int cal;

    public Food(int id, String name, int cal, String tag) {
        this.id = id;
        this.name = name;
        this.cal = cal;
        this.tag = tag;
    }

    public Food() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCal() {
        return cal;
    }

    public void setCal(int cal) {
        this.cal = cal;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    private String tag;
}
