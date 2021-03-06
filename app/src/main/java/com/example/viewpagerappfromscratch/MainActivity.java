package com.example.viewpagerappfromscratch;

import android.annotation.SuppressLint;
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

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends FragmentActivity {
    private static final String TAG = "MainActivity";
    Disposable retrofit;
    List<Fragment> fragmentList = new ArrayList<>();

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        retrofit = RetrofitSingleton.getInstance()
                .create(ZodiacService.class)
                .getZodiacList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(zodiacList -> {
                    List<Zodiac> zodiacs = zodiacList.getZodiacList();
                    for (Zodiac zodiac : zodiacs) {
                        fragmentList.add(MainFragment.newInstance(zodiac.getName(),
                                zodiac.getNumber(),
                                zodiac.getImage()));
                        ViewPager viewPager = findViewById(R.id.view_pager);
                        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), fragmentList));
                    }
                }, throwable -> Log.d(TAG, "onFailure: ---" + throwable.getMessage()));
    }
}
