package com.studiopixidream.kalory.model;

import java.io.Serializable;

public class Day implements Serializable {

    private Integer id;
    private String name;
    private String tkcal;
    private Integer weekid;

    public Day() {
    }

    public Day(Integer id, String name, String tkcal, Integer weekid) {
        this.id = id;
        this.name = name;
        this.tkcal = tkcal;
        this.weekid = weekid;
    }

    public Day(String name, String tkcal, Integer weekid) {
        this.name = name;
        this.tkcal = tkcal;
        this.weekid = weekid;
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

    public String getTkcal() {
        return this.tkcal;
    }

    public void setTkcal(String tkcalkl) {
        this.tkcal = tkcalkl;
    }

    public Integer getWeekid() {
        return weekid;
    }

    public void setWeekid(Integer weekid) {
        this.weekid = weekid;
    }
}