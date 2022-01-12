package com.example.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BooksActivity extends AppCompatActivity {
    LinearLayout linearLayoutBookList;
    LayoutInflater inflater;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        TextView textViewBookCategoryTitle = findViewById(R.id.tv_book_category_title);
        ImageView imageViewBack2 = findViewById(R.id.image_view_back2);
        ImageView imageViewCart = findViewById(R.id.image_view_cart);
        SearchView searchViewBook = findViewById(R.id.search_view_book);

        imageViewBack2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        imageViewCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CheckoutActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutBookList = findViewById(R.id.linear_layout_book_list);
        inflater = LayoutInflater.from(this);

        Intent intent = getIntent();
        if (intent.hasExtra("category")) {
            Bundle extras = getIntent().getExtras();
            textViewBookCategoryTitle.setText(extras.getString("category"));
            String url = "https://book-api-hoanganleba.vercel.app/books?category=" + extras.getString("category");
            fetchBooksData(url);
        } else {
            textViewBookCategoryTitle.setText("All Category");
            String url = "https://book-api-hoanganleba.vercel.app/books";
            fetchBooksData(url);
        }

        searchViewBook.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchViewBook.clearFocus();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                linearLayoutBookList.removeAllViews();
                String url;
                if (s.equals("")) {
                    if (intent.hasExtra("category")) {
                        Bundle extras = getIntent().getExtras();
                        url = "https://book-api-hoanganleba.vercel.app/books?category=" + extras.getString("category");
                    } else {
                        url = "https://book-api-hoanganleba.vercel.app/books";
                    }
                } else {
                    if (intent.hasExtra("category")) {
                        Bundle extras = getIntent().getExtras();
                        url = "https://book-api-hoanganleba.vercel.app/books/search?title=" + s + "&category=" + extras.getString("category");
                    } else {
                        url = "https://book-api-hoanganleba.vercel.app/books/search?title=" + s;
                    }
                }
                fetchBooksData(url);
                return false;
            }
        });
    }

    public void fetchBooksData(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {

                    CardView linearLayoutBookItem = (CardView) inflater.inflate(R.layout.book_item, linearLayoutBookList, false);
                    TextView textViewCardBookTitle = linearLayoutBookItem.findViewById(R.id.tv_card_book_title);
                    TextView textViewCardAuthor = linearLayoutBookItem.findViewById(R.id.tv_card_author);
                    TextView textViewCardPrice = linearLayoutBookItem.findViewById(R.id.tv_card_price);
                    ImageView imageViewCardBook = linearLayoutBookItem.findViewById(R.id.image_view_card_book);

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String title = jsonObject.getString("title");
                        String author = jsonObject.getString("author");
                        String price = jsonObject.getString("price");
                        String image = jsonObject.getString("image");
                        String description = jsonObject.getString("description");

                        textViewCardBookTitle.setText(title);
                        textViewCardAuthor.setText(author);
                        textViewCardPrice.setText(price);
                        Picasso.get().load(image).into(imageViewCardBook);

                        linearLayoutBookList.addView(linearLayoutBookItem);

                        linearLayoutBookItem.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(getApplicationContext(), BookDetailActivity.class);
                                intent.putExtra("title", title);
                                intent.putExtra("author", author);
                                intent.putExtra("price", price);
                                intent.putExtra("image", image);
                                intent.putExtra("description", description);
                                startActivity(intent);
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
        requestQueue.add(jsonArrayRequest);
    }
}