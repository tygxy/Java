package com.javaEE.Bean;

/**
 * Created by guoxingyu on 2018/5/21.
 */
public class MealType {
    private int id;
    private String mealName;
    private double price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMealName() {
        return mealName;
    }

    public void setMealName(String mealName) {
        this.mealName = mealName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
