package com.example.appmo.Order;


public class Order {
    private String date;
    private String nameOrder;
    private String destinationOrder;
    private String type;
    private String nameBread;
    private String qualitityOrder;

    public Order(String date, String nameOrder,
                 String destinationOrder, String type,
                 String nameBread, String qualitityOrder) {
        this.date = date;
        this.nameOrder = nameOrder;
        this.destinationOrder = destinationOrder;
        this.type = type;
        this.nameBread = nameBread;
        this.qualitityOrder = qualitityOrder;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNameOrder() {
        return nameOrder;
    }

    public void setNameOrder(String nameOrder) {
        this.nameOrder = nameOrder;
    }

    public String getDestinationOrder() {
        return destinationOrder;
    }

    public void setDestinationOrder(String destinationOrder) {
        this.destinationOrder = destinationOrder;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNameBread() {
        return nameBread;
    }

    public void setNameBread(String nameBread) {
        this.nameBread = nameBread;
    }

    public String getQualitityOrder() {
        return qualitityOrder;
    }

    public void setQualitityOrder(String qualitityOrder) {
        this.qualitityOrder = qualitityOrder;
    }
}

