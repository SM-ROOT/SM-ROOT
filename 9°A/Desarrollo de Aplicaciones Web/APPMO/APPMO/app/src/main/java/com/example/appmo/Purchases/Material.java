package com.example.appmo.Purchases;


public class Material {
    String name;
    String date;
    String quantity;
    String measure;
    String coste;

    public Material(String name, String date, String quantity, String measure, String coste) {
        this.name = name;
        this.date = date;
        this.quantity = quantity;
        this.measure = measure;
        this.coste = coste;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getCoste() {
        return coste;
    }

    public void setCoste(String coste) {
        this.coste = coste;
    }
}
