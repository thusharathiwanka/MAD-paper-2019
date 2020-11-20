package com.example.a2019_paper_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MovieOverviewActivity extends AppCompatActivity {
    SeekBar movieRate;
    ListView commentList;
    DBHandler dbHandler;
    Button btnSubmit;
    TextView movieRateValue, title;
    EditText comment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_overview);

        dbHandler = new DBHandler(this);
        commentList = findViewById(R.id.lvComments);
        btnSubmit = findViewById(R.id.btnSubmit);
        movieRate = findViewById(R.id.movieRate);
        movieRateValue = findViewById(R.id.tvNumberOfRatings);
        title = findViewById(R.id.tvTitle);
        comment = findViewById(R.id.etComment);

        Intent intent = getIntent();
        String movieName = intent.getStringExtra("MOVIE_NAME");
        title.setText(movieName);

        dbHandler.viewComments(movieName);
        ArrayList<String> comments = dbHandler.getComments();
        System.out.println(comments);
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, comments);
        commentList.setAdapter(adapter);


        movieRate.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                movieRateValue.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isAdded = dbHandler.insertComments(movieName, Integer.parseInt(movieRateValue.getText().toString()), comment.getText().toString());

                if(isAdded) {
                    Toast.makeText(MovieOverviewActivity.this, "Your review added", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MovieOverviewActivity.this, "Your review not added", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}