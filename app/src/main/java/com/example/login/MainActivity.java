package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    CarouselView carouselView;
    Button loginBtn;
    TextView signupBtn;
    EditText personName, personPassword;
    private int[] mImages = new int[] {
            R.drawable.bear, R.drawable.penguin
    };

    private String[] mImagesTitle = new String[] {
            "Bear", "Penguin"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginBtn = findViewById(R.id.buttonLgn);
        signupBtn = (TextView) findViewById(R.id.buttonSgn);
        personName = findViewById(R.id.editTextPersonName);
        personPassword = findViewById(R.id.editTextPassword);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://book-api-hoanganleba.vercel.app/auth/login";
                String username = personName.getText().toString();
                String password = personPassword.getText().toString();

                if (username.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
                } else {
                    RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // get success message from server
                            try {
                                JSONObject jsonObject = new JSONObject(response);
                                if(jsonObject.getString("status").equals("Success")) {
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                                    // Redirect to homepage after login success
                                    Intent intent = new Intent(getApplicationContext(), Home.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            // get message error from server
                            Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        public Map<String, String> getHeaders() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("Content-Type", "application/x-www-form-urlencoded");
                            return params;
                        }

                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("username", username);
                            params.put("password", password);
                            return params;
                        }
                    };
                    requestQueue.add(stringRequest);
                }
            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Signup.class);
                startActivity(intent);
            }
        });

    }
}