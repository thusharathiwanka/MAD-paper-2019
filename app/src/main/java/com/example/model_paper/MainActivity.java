package com.example.model_paper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password;
    Button btnLogin, btnRegister;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DBHelper(this);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long userID = dbHelper.addInfo(username.getText().toString(), password.getText().toString());

                if (userID != -1) {
                    Intent intent = new Intent(getApplicationContext(), ProfileManagemnentActivity.class);
                    intent.putExtra("ROW_ID", String.valueOf(userID));
                    startActivity(intent);
                    Toast.makeText(MainActivity.this, "User added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "User not added", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.loginUser(username.getText().toString(), password.getText().toString());
                System.out.println(cursor.getCount());

                if (cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        String userID = cursor.getString(0);
                        Intent intent = new Intent(getApplicationContext(), EditProfileActivity.class);
                        intent.putExtra("USER_ID", userID);
                        startActivity(intent);
                    }
                } else {
                    Toast.makeText(MainActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}