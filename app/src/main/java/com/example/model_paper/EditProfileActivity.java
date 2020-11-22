package com.example.model_paper;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class EditProfileActivity extends AppCompatActivity {
    EditText username, password, dob;
    RadioButton male, female;
    Button btnEdit, btnDelete, btnSearch;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        dbHelper = new DBHelper(this);
        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        dob = findViewById(R.id.etBirthday);
        male = findViewById(R.id.rbMale);
        female = findViewById(R.id.rbFemale);
        btnEdit = findViewById(R.id.btnEdit);
        btnSearch = findViewById(R.id.btnSearch);
        btnDelete = findViewById(R.id.btnDelete);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("USER_ID");

        btnEdit.setOnClickListener(new View.OnClickListener() {
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
                    Toast.makeText(EditProfileActivity.this, "User updated", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "User not updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dbHelper.deleteInfo(username.getText().toString())) {
                    Toast.makeText(EditProfileActivity.this, "User deleted", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(EditProfileActivity.this, "User not in the database", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor cursor = dbHelper.readAllInfo(username.getText().toString());

                if(cursor.getCount() > 0) {
                    while (cursor.moveToNext()) {
                        dob.setText(cursor.getString(3));
                        password.setText(cursor.getString(2));

                        if(cursor.getString(4).equalsIgnoreCase("male")) {
                            male.toggle();
                        } else {
                            female.toggle();
                        }
                    }
                } else {
                    Toast.makeText(EditProfileActivity.this, "No user found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}