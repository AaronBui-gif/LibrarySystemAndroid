package com.example.login;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

public class Home extends AppCompatActivity {

    CarouselView carouselView;
    ImageView userProfile;
    ImageButton primaryBtn, secondaryBtn, thirdBtn, fourthBtn;
    int[] sampleImages = {R.drawable.group_16659, R.drawable.library_photos};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_book);

        userProfile = (ImageView) findViewById(R.id.userProfile);
        carouselView = (CarouselView) findViewById(R.id.carouselView);
        carouselView.setPageCount(sampleImages.length);
        primaryBtn = findViewById(R.id.primaryBtn);
        secondaryBtn = findViewById(R.id.upperPrimaryBtn);
        thirdBtn = findViewById(R.id.highSchoolBtn);
        fourthBtn = findViewById(R.id.higherSecondaryBtn);
        carouselView.setImageListener(imageListener);

        userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, UserDetails.class);
                startActivity(intent);
            }
        });

        primaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Home.this, Books.class);
                startActivity(intent1);
            }
        });

        secondaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        thirdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        fourthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            imageView.setImageResource(sampleImages[position]);
        }
    };


}