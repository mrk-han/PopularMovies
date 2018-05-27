package com.nanodegree.markhan.popularmovies;

import android.graphics.Movie;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.movie_thumbail_recyclerview) RecyclerView movieRecyclerView;

//    ArrayList<> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize movies
        // TODO: make an async call to fetch the most popular 20 movies from api and put them into ArrayList

        // Create adapter passing in the movie data
        MovieAdapter adapter = new MovieAdapter(null);

        // Attach the adapter to the recyclerview to populate items
        movieRecyclerView.setAdapter(adapter);
        movieRecyclerView.setHasFixedSize(true); // may improve performance depending on size of recyclerview
        movieRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }
}
