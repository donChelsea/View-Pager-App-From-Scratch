package com.example.viewpagerappfromscratch.model;

public class Zodiac {
    private String name;
    private String number;
    private String image;

    public Zodiac(String name, String number, String image) {
        this.name = name;
        this.number = number;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getImage() {
        return image;
    }
}
