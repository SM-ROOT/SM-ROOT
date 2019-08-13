package com.example.appmo.Client;

public class Client {

    /**
     * Modelo de la entidades del modulo de clientes con constructor, get y set
     **/
    String name;
    String phone;
    String adrres;
    String numberAdrres;
    String cp;
    String colony;
    String city;
    String state;

    public Client(String name, String phone, String adrres,
                  String numberAdrres, String cp, String colony,
                  String city, String state) {
        this.name = name;
        this.phone = phone;
        this.adrres = adrres;
        this.numberAdrres = numberAdrres;
        this.cp = cp;
        this.colony = colony;
        this.city = city;
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdrres() {
        return adrres;
    }

    public void setAdrres(String adrres) {
        this.adrres = adrres;
    }

    public String getNumberAdrres() {
        return numberAdrres;
    }

    public void setNumberAdrres(String numberAdrres) {
        this.numberAdrres = numberAdrres;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
