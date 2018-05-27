package com.nanodegree.markhan.popularmovies;

import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends
        RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movies;

    public MovieAdapter(List<Movie> movies) {
        this.movies = movies;
    }

    // Inflate the custom layout from XML and return the holder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View movieView = inflater.inflate(R.layout.movie_poster, parent, false);

        // Return a new holder instance
        return new ViewHolder(movieView);
    }

    // Populates data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Movie movie = movies.get(position);

        // Set item views based on your views and data model
        // TODO: Use Picasso to fetch Image



    }

    // Returns the total count of movies in the list
    @Override
    public int getItemCount() {
        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movie_poster_imageview) ImageView moviePosterIV;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, moviePosterIV);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }
}
