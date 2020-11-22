package com.example.model_paper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class ProfileManagemnentActivity extends AppCompatActivity {
    EditText username, password, dob;
    RadioButton male, female;
    Button btnUpdate;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_managemnent);

        dbHelper = new DBHelper(this);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        dob = findViewById(R.id.etBirthday);
        male = findViewById(R.id.rbMale);
        female = findViewById(R.id.rbFemale);
        btnUpdate = findViewById(R.id.btnUpdate);

        Intent intent = getIntent();
        final String userID = intent.getStringExtra("ROW_ID");

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String gender = "null";

                if (male.isChecked()) {
                    gender = "male";
                } else if (female.isChecked()) {
                    gender = "female";
                }
                boolean isUpdated = dbHelper.updateInfo(userID, username.getText().toString(), dob.getText().toString(), password.getText().toString(),
                        gender);

                if (isUpdated) {
                    Toast.makeText(ProfileManagemnentActivity.this, "User updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ProfileManagemnentActivity.this, "User not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}