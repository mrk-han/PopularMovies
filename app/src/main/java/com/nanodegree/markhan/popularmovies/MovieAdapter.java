package com.nanodegree.markhan.popularmovies;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.nanodegree.markhan.popularmovies.models.Movie;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends
        RecyclerView.Adapter<MovieAdapter.ViewHolder> {


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, moviePosterIV);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }

    private List<Movie> movieList;
    private static final String MOVIE_POSTER_PATH = "http://image.tmdb.org/t/p/w342//to0spRl1CMDvyUbOnbb4fTk3VAd.jpg";
    @BindView(R.id.movie_poster_imageview) ImageView moviePosterIV;


    public MovieAdapter(List<Movie> movieList) {
        this.movieList = movieList;
    }

    // Inflate the custom layout from XML and return the holder
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
//        Movie movie = movieList.get(position);
        String posterUrl = MOVIE_POSTER_PATH;
        Picasso.get()
                .load(posterUrl)
                .placeholder(R.color.colorAccent)
                .into(moviePosterIV);
        // TODO need to place the images from picasso into holder.imageview1 and holder.imageview2 which are the 2 image views in the row
    }

    // Returns the total count of movieList in the list
    @Override
    public int getItemCount() {
        return movieList.size();
    }


}
