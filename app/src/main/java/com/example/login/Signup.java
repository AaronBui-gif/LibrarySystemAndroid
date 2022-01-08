package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    Button saveBtn, goBackBtn;
    EditText name, password, repassword, age;
    Boolean insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        saveBtn = findViewById(R.id.saveBtn);
        goBackBtn = findViewById(R.id.goBackBtn);
        name = findViewById(R.id.editTextPersonName);
        password = findViewById(R.id.editTextPassword);
        repassword = findViewById(R.id.editTextRepassword);
//        age = findViewById(R.id.editTextAge); no need age field

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://book-api-hoanganleba.vercel.app/auth/register";
                String username = name.getText().toString();
                String passwordSgn = password.getText().toString();
                String repasswordSgn = repassword.getText().toString();

                if (username.equals("") || repasswordSgn.equals("") || passwordSgn.equals("")) {
                    Toast.makeText(Signup.this, "Cannot input empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (passwordSgn.equals(repasswordSgn)) {
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                // get success message from server
                                try {
                                    JSONObject jsonObject = new JSONObject(response);
                                    if (jsonObject.getString("status").equals("Success")) {
                                        Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_SHORT).show();

                                        // Redirect to login page after register success
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
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
                                params.put("password", passwordSgn);
                                return params;
                            }
                        };
                        requestQueue.add(stringRequest);
                    } else {
                        Toast.makeText(Signup.this, "Passwords not matching", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        goBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Signup.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}