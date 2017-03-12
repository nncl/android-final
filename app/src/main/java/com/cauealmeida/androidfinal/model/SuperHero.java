package com.cauealmeida.androidfinal.model;

/**
 * Created by cauealmeida on 3/12/17.
 */

public class SuperHero {
    private String name;
    private String brand;

    public SuperHero(String name, String brand) {
        this.name = name;
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
