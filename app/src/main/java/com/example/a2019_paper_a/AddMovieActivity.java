package com.example.a2019_paper_a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddMovieActivity extends AppCompatActivity {
    EditText movieName, movieYear;
    Button btnAdd;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        dbHandler = new DBHandler(this);
        movieName = findViewById(R.id.etMovieName);
        movieYear = findViewById(R.id.etMovieYear);
        btnAdd = findViewById(R.id.btnAddMovie);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAdded = dbHandler.addMovies(movieName.getText().toString(), Integer.parseInt(movieYear.getText().toString()));

                if(isAdded) {
                    Toast.makeText(AddMovieActivity.this, "Movie added", Toast.LENGTH_SHORT).show();
                    movieName.setText("");
                    movieYear.setText("");
                } else {
                    Toast.makeText(AddMovieActivity.this, "Movie not added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}