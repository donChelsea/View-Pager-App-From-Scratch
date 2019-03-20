package com.example.viewpagerappfromscratch.network;

import com.example.viewpagerappfromscratch.model.ZodiacList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ZodiacService {
    @GET("JDVila/storybook/master/zodiac.json")
    Call<ZodiacList> getZodiacList();
}
