package com.example.appmo.Employee;

public class User {
    private String name;
    private String subNamePather;
    private String subNameMother;
    private String type;
    private String phone;
    private String addres;
    private String addresNumber;
    private String cp;
    private String colony;
    private String state;
    private String city;

    public User(String name, String subNamePather, String subNameMother, String type, String phone,
                String addres, String addresNumber, String cp, String colony,
                String state, String city) {
        this.name = name;
        this.subNamePather = subNamePather;
        this.subNameMother = subNameMother;
        this.type = type;
        this.phone = phone;
        this.addres = addres;
        this.addresNumber = addresNumber;
        this.cp = cp;
        this.colony = colony;
        this.state = state;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubNamePather() {
        return subNamePather;
    }

    public void setSubNamePather(String subNamePather) {
        this.subNamePather = subNamePather;
    }

    public String getSubNameMother() {
        return subNameMother;
    }

    public void setSubNameMother(String subNameMother) {
        this.subNameMother = subNameMother;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getAddresNumber() {
        return addresNumber;
    }

    public void setAddresNumber(String addresNumber) {
        this.addresNumber = addresNumber;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
