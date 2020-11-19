package com.example.a2019_paper_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin, btnRegister;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(this);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long isInserted = dbHandler.registerUser(username.getText().toString(), password.getText().toString());

                if (isInserted == -1) {
                    Toast.makeText(MainActivity.this, "User not registered. Something went wrong.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User registered.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String isExists = dbHandler.loginUser(username.getText().toString(), password.getText().toString());

                if(isExists.equals("not_exists")) {
                    Toast.makeText(MainActivity.this, "Login failed. Username and password not exists", Toast.LENGTH_SHORT).show();
                } else if(isExists.equals("not_matches")) {
                    Toast.makeText(MainActivity.this, "Login failed, Username and password not matching", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent;

                    if(username.getText().toString().equals("admin")) {
                        intent = new Intent(getApplicationContext(), AddMovieActivity.class);
                    } else {
                        intent = new Intent(getApplicationContext(), MovieListActivity.class);
                    }

                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "Logged in", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}