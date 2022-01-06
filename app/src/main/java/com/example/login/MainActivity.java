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

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

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

        Database db = new Database(this);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = personName.getText().toString();
                String password = personPassword.getText().toString();

                if (user.equals("") || password.equals("")){
                    Toast.makeText(MainActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();

                } else {
                    Boolean checkUserPass = db.checkUsernamePassword(user, password);
                    if (checkUserPass == true) {
                        Toast.makeText(MainActivity.this, "Sign in Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomePage.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
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