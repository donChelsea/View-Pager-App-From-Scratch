package com.example.viewpagerappfromscratch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;

import com.example.viewpagerappfromscratch.controller.ViewPagerAdapter;
import com.example.viewpagerappfromscratch.frag.MainFragment;
import com.example.viewpagerappfromscratch.model.Zodiac;
import com.example.viewpagerappfromscratch.model.ZodiacList;
import com.example.viewpagerappfromscratch.network.RetrofitSingleton;
import com.example.viewpagerappfromscratch.network.ZodiacService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    Retrofit retrofit;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = RetrofitSingleton.getInstance();
        ZodiacService zodiacService = retrofit.create(ZodiacService.class);
        Call<ZodiacList> zodiacListCall = zodiacService.getZodiacList();
        zodiacListCall.enqueue(new Callback<ZodiacList>() {
            @Override
            public void onResponse(Call<ZodiacList> call, Response<ZodiacList> response) {
                assert response.body() != null;
                List<Zodiac> zodiacs = response.body().getZodiacList();
                for (Zodiac zodiac: zodiacs) {
                    fragmentList.add(MainFragment.newInstance(zodiac.getName(),
                            zodiac.getNumber(),
                            zodiac.getImage()));
                    ViewPager viewPager = findViewById(R.id.view_pager);
                    viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList));
                }
            }

            @Override
            public void onFailure(Call<ZodiacList> call, Throwable t) {
                Log.d(TAG, "onFailure: ---" + t.getMessage());
            }
        });
    }
}
