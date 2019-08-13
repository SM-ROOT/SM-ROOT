package com.example.appmo.Recipe;

public class Recipe {
    String name;
    String ingredient;
    String quantity;
    String measure;
    String type;

    public Recipe(String name, String ingredient,
                  String quantity, String measure, String type) {
        this.name = name;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.measure = measure;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
