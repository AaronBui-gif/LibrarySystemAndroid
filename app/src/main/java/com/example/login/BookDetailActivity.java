package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        Bundle extras = getIntent().getExtras();

        TextView textViewBookTitle = findViewById(R.id.tv_book_title);
        TextView textViewBookAuthor = findViewById(R.id.tv_book_author);
        TextView textViewBookDetail = findViewById(R.id.tv_book_detail);
        TextView textViewPrice = findViewById(R.id.tv_book_price);

        ImageView imageViewBackDark = findViewById(R.id.image_view_back_dark);
        ImageView imageViewBook = findViewById(R.id.image_view_book);

        textViewBookTitle.setText(extras.getString("title"));
        textViewBookAuthor.setText(extras.getString("author"));
        textViewBookDetail.setText(extras.getString("description"));
        textViewPrice.setText(extras.getString("price"));
        Picasso.get().load(extras.getString("image")).into(imageViewBook);

        imageViewBackDark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button buttonPurchase = findViewById(R.id.btn_purchase);
        buttonPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });
    }
}