package com.example.a2019_paper_a;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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

        System.out.println(movies);

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, movies);
        movieList.setAdapter(adapter);
    }
}