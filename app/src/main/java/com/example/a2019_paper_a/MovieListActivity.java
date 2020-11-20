package com.example.a2019_paper_a;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MovieListActivity extends AppCompatActivity {
    ListView movieList;
    DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        dbHandler = new DBHandler(this);
        movieList = findViewById(R.id.lvMovies);

        dbHandler.viewMovies();
        ArrayList<String> movies = dbHandler.getMovies();
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
        movieList.setAdapter(adapter);

        movieList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String movieName = parent.getItemAtPosition(position).toString();

                Intent intent = new Intent(getApplicationContext(), MovieOverviewActivity.class);
                intent.putExtra("MOVIE_NAME", movieName);
                startActivity(intent);
            }
        });
    }
}