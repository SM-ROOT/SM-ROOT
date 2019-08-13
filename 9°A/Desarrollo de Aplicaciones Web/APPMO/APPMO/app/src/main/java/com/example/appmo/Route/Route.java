package com.example.appmo.Route;

public class Route {
    String name;
    String Curator;

    public Route(String name, String curator) {
        this.name = name;
        Curator = curator;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurator() {
        return Curator;
    }

    public void setCurator(String curator) {
        Curator = curator;
    }
}
