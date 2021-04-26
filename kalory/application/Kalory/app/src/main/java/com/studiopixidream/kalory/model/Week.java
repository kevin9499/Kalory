package com.studiopixidream.kalory.model;

import java.io.Serializable;
import java.util.Date;

public class Week implements Serializable {

    private int id;
    private String name;

    public Week() {
    }

    public Week(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Week(String name) {
        this.name = name;
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
}