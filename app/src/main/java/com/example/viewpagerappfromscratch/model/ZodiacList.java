package com.example.viewpagerappfromscratch.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ZodiacList {
    @SerializedName("zodiac")

    private List<Zodiac> zodiacList;

    public ZodiacList(List<Zodiac> zodiacList) {
        this.zodiacList = zodiacList;
    }

    public List<Zodiac> getZodiacList() {
        return zodiacList;
    }
}
