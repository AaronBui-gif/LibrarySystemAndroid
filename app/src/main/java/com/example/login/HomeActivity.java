package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    LinearLayout linearLayoutLowerPrimary, linearLayoutUpperPrimary, linearLayoutHighSchool, linearLayoutHigherSecondary, linearLayoutBanner;
    ImageView imageViewCart;
    TextView textViewUserNameTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        linearLayoutLowerPrimary = findViewById(R.id.linear_layout_lower_primary);
        linearLayoutUpperPrimary = findViewById(R.id.linear_layout_upper_primary);
        linearLayoutHighSchool = findViewById(R.id.linear_layout_high_school);
        linearLayoutHigherSecondary = findViewById(R.id.linear_layout_higher_secondary);
        linearLayoutBanner = findViewById(R.id.linear_layout_banner);

        imageViewCart = findViewById(R.id.image_view_cart);
        textViewUserNameTitle = findViewById(R.id.tv_username_title);

        SharedPreferences sharedPreferences = getSharedPreferences("Profile", Context.MODE_PRIVATE);
        textViewUserNameTitle.setText(sharedPreferences.getString("username", null));

        textViewUserNameTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyAccountActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BooksActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutLowerPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BooksActivity.class);
                intent.putExtra("category", "Lower Primary");
                startActivity(intent);
            }
        });

        linearLayoutUpperPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BooksActivity.class);
                intent.putExtra("category", "Upper Primary");
                startActivity(intent);
            }
        });

        linearLayoutHighSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BooksActivity.class);
                intent.putExtra("category", "High School");
                startActivity(intent);
            }
        });

        linearLayoutHigherSecondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BooksActivity.class);
                intent.putExtra("category", "Higher Secondary");
                startActivity(intent);
            }
        });

        imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

    }
}