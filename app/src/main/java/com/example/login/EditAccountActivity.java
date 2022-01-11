package com.example.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditAccountActivity extends AppCompatActivity {
    TextView textViewUserName;
    EditText editTextFirstName, editTextLastName, editTextPhoneNumber, editTextAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_account);
        textViewUserName = findViewById(R.id.tv_username);
        textViewUserName = findViewById(R.id.tv_username);
        editTextFirstName = findViewById(R.id.edt_first_name);
        editTextLastName = findViewById(R.id.edt_last_name);
        editTextPhoneNumber = findViewById(R.id.edt_phone_number);
        editTextAddress = findViewById(R.id.edt_andress);

        SharedPreferences sharedPreferences = getSharedPreferences("Profile", Context.MODE_PRIVATE);
        textViewUserName.setText(sharedPreferences.getString("username", null));
        editTextFirstName.setText(sharedPreferences.getString("firstname", null));
        editTextLastName.setText(sharedPreferences.getString("lastname", null));
        editTextAddress.setText(sharedPreferences.getString("address", null));
        editTextPhoneNumber.setText(sharedPreferences.getString("phoneNumber", null));

        ImageView imageViewBack = findViewById(R.id.image_view_back);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Button btnSaveUser = findViewById(R.id.btn_save_user);
        btnSaveUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                String url = "https://book-api-hoanganleba.vercel.app/users/" + sharedPreferences.getString("userId", null);;
                String firstname = editTextFirstName.getText().toString();
                String lastname = editTextLastName.getText().toString();
                String address = editTextAddress.getText().toString();
                String phoneNumber = editTextPhoneNumber.getText().toString();

                StringRequest stringRequest = new StringRequest(Request.Method.PATCH, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getApplicationContext(), "User update successfully" ,Toast.LENGTH_LONG).show();
                        @SuppressLint("CommitPrefEdits") SharedPreferences.Editor editor = sharedPreferences.edit();

                        editor.putString("address", address);
                        editor.putString("firstname", firstname);
                        editor.putString("lastname", lastname);
                        editor.putString("phoneNumber", phoneNumber);

                        editor.apply();
                        Intent intent = new Intent(getApplicationContext(), MyAccountActivity.class);
                        startActivity(intent);
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), error.getMessage() ,Toast.LENGTH_LONG).show();
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
                        Map<String, String> params = new HashMap<>();
                        params.put("firstname", firstname);
                        params.put("lastname", lastname);
                        params.put("address", address);
                        params.put("phoneNumber", phoneNumber);
                        return params;
                    }
                };
                requestQueue.add(stringRequest);
            }
        });
    }
}