package com.example.appmo.Supply;

public class Supply {
    private String rfc;
    private String phone;
    private String mail;
    private String addres;
    private String numAddres;
    private String cp;
    private String colony;
    private String city;
    private String state;

    public Supply(String rfc, String phone,
                  String mail, String addres, String numAddres,
                  String cp, String colony, String city, String state) {
        this.rfc = rfc;
        this.phone = phone;
        this.mail = mail;
        this.addres = addres;
        this.numAddres = numAddres;
        this.cp = cp;
        this.colony = colony;
        this.city = city;
        this.state = state;
    }


    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getAddres() {
        return addres;
    }

    public void setAddres(String addres) {
        this.addres = addres;
    }

    public String getNumAddres() {
        return numAddres;
    }

    public void setNumAddres(String numAddres) {
        this.numAddres = numAddres;
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
