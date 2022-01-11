package com.example.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MyAccountActivity extends AppCompatActivity {

    ImageView imageViewBack;
    TextView textViewUserName, textViewEdit, textViewName, textViewAddress, textViewPhoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_account);
        imageViewBack = findViewById(R.id.image_view_back);
        textViewEdit = findViewById(R.id.tv_edit);
        textViewUserName = findViewById(R.id.tv_username);
        textViewName = findViewById(R.id.tv_name);
        textViewAddress = findViewById(R.id.tv_andress);
        textViewPhoneNumber = findViewById(R.id.tv_phone_number);

        SharedPreferences sharedPreferences = getSharedPreferences("Profile", Context.MODE_PRIVATE);
        textViewUserName.setText(sharedPreferences.getString("username", null));
        String name = sharedPreferences.getString("firstname", null) + " " + sharedPreferences.getString("lastname", null);
        textViewName.setText(name);
        textViewAddress.setText(sharedPreferences.getString("address", null));
        textViewPhoneNumber.setText(sharedPreferences.getString("phoneNumber", null));

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                startActivity(intent);
            }
        });

        textViewEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditAccountActivity.class);
                startActivity(intent);
            }
        });
    }
}