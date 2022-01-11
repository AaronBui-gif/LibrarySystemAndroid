package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    LinearLayout linearLayoutLowerPrimary, linearLayoutUpperPrimary, linearLayoutHighSchool, linearLayoutHigherSecondary;
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

        imageViewCart = findViewById(R.id.image_view_cart);
        textViewUserNameTitle = findViewById(R.id.tv_username_title);

        textViewUserNameTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyAccountActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutLowerPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        linearLayoutUpperPrimary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        linearLayoutHighSchool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        linearLayoutHigherSecondary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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