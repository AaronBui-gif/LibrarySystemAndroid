package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
        age = findViewById(R.id.editTextAge);
        Database db = new Database(this);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = name.getText().toString();
                String passwordSgn = password.getText().toString();
                String repasswordSgn = repassword.getText().toString();

                if (user.equals("") || repasswordSgn.equals("") || passwordSgn.equals("")){
                    Toast.makeText(Signup.this, "Cannot input empty", Toast.LENGTH_SHORT).show();
                } else {
                    if (passwordSgn.equals(repasswordSgn)) {
                        Boolean checkUser = db.checkUsername(user);
                        if (checkUser == false) {
                            insert = db.insertData(user, passwordSgn);
                            if (insert == true) {
                                Toast.makeText(Signup.this, "Register Successfully", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(Signup.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(Signup.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(Signup.this, "User already exists. Please enter again.", Toast.LENGTH_SHORT).show();
                        }
                    } else{
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