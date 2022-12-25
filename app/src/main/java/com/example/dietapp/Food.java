package com.example.dietapp;

public class Food {
    private String name;
    private int calories;
    private String amount;
    private boolean selected;

    public Food() {
    }

    public Food(String name, int calories, String amount, boolean selected) {
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

    public String getAmount() { // getter for the amount field
        return amount;
    }

    public void setAmount(String amount) { // setter for the amount field
        this.amount = amount;
    }

    public boolean isSelected() { // new getter for the selected field
        return selected;
    }

    public void setSelected(boolean selected) { // new setter for the selected field
        this.selected = selected;
    }
}
