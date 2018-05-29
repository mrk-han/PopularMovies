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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieAdapter extends
        RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private List<Movie> movieList;
    private LayoutInflater inflater;
    private Context context;
    private static final String MOVIE_POSTER_PATH = "http://image.tmdb.org/t/p/w342/";



    public MovieAdapter(Context context) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.movieList = new ArrayList<>();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.movie_poster_imageview)
        ImageView moviePosterIV;
        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, moviePosterIV);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
        }
    }


    // Inflate the custom layout from XML and return the holder
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View movieView = inflater.inflate(R.layout.movie_poster, parent, false);

        // Return a new holder instance
        return new ViewHolder(movieView);
    }

    // Populates data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        // Get the data model based on position
        Movie movie = movieList.get(position);
        String posterUrl = MOVIE_POSTER_PATH + movie.getPosterPath();
        Picasso.get()
                .load(posterUrl)
                .placeholder(R.color.colorAccent)
                .into(holder.moviePosterIV);
    }

    // Returns the total count of movieList in the list
    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList.clear();
        this.movieList.addAll(movieList);
        notifyDataSetChanged();
    }


}
