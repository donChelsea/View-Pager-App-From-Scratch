package com.example.viewpagerappfromscratch.frag;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.viewpagerappfromscratch.R;
import com.squareup.picasso.Picasso;


public class MainFragment extends Fragment {
    private static final String ZODIAC_NAME = "zodiac name";
    private static final String ZODIAC_NUMBER = "zodiac number";
    private static final String ZODIAC_IMAGE = "zodiac image";
    private TextView nameTv;
    private TextView numberTv;
    private ImageView imageView;
    String name;
    String number;
    String image;

    public MainFragment() {
    }

    public static MainFragment newInstance(String name, String number, String image) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ZODIAC_NAME, name);
        args.putString(ZODIAC_NUMBER, number);
        args.putString(ZODIAC_IMAGE, image);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(ZODIAC_NAME);
            number = getArguments().getString(ZODIAC_NUMBER);
            image = getArguments().getString(ZODIAC_IMAGE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        nameTv = view.findViewById(R.id.name_textview);
        numberTv = view.findViewById(R.id.number_textview);
        imageView = view.findViewById(R.id.imageView);

        nameTv.setText(name);
        numberTv.setText(number);
        Picasso.get().load(image).into(imageView);
    }

}
