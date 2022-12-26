package com.example.dietapp;

public class Food {
    private String name;
    private int calories;
    private int amount = 1;
    private boolean selected;

    public int getTotalCalories() {
        return totalCalories;
    }

    public void setTotalCalories() {
        this.totalCalories = calories * amount;
    }

    private int totalCalories;

    public Food() {
    }

    public Food(String name, int calories, int amount, boolean selected) {
        this.name = name;
        this.calories = calories;
        this.amount = amount;
        this.selected = selected;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getAmount() { // getter for the amount field
        return amount;
    }

    public void setAmount(int amount) { // setter for the amount field
        this.amount = amount;
    }

    public boolean isSelected() { // new getter for the selected field
        return selected;
    }

    public void setSelected(boolean selected) { // new setter for the selected field
        this.selected = selected;
    }
}
