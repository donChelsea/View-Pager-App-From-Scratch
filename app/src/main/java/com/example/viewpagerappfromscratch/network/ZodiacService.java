package com.example.viewpagerappfromscratch.network;

import com.example.viewpagerappfromscratch.model.ZodiacList;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ZodiacService {
    @GET("JDVila/storybook/master/zodiac.json")
    Observable<ZodiacList> getZodiacList();
}
