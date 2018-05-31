package com.nanodegree.markhan.popularmovies;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    private static final String TAG = MovieAdapter.class.getSimpleName();
    private List<Movie> movieList;
    private LayoutInflater inflater;
    private Context context;
    private static final String MOVIE_POSTER_PATH = "http://image.tmdb.org/t/p/w342/";



    public MovieAdapter(Context context, List<Movie> movieList) {
        this.context = context;
        this.movieList = new ArrayList<>();
    }


    // Our custom ViewHolder class contains an ImageView to which we attach an on click listener to listen for when the user clicks an image.
    // The ViewHolder also contains information about its place within the parent RecyclerView.
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_poster_imageview) ImageView moviePosterIV;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


    // Inflate the custom layout from XML and return the holder
    // creates a new ViewHolder containing our image
    @NonNull
    @Override
    public MovieAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        inflater = LayoutInflater.from(context);

        // Inflate custom layout
        View movieView = inflater.inflate(R.layout.movie_poster, viewGroup, false);

        // Return a new holder instance
        return new ViewHolder(movieView);
    }

    // Populates data into the item through holder
    // displays our image at the specified position in the list
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Movie movie = movieList.get(position);
        String posterUrl = (MOVIE_POSTER_PATH + movie.getPosterPath());
        Log.d(TAG, MOVIE_POSTER_PATH + movie.getPosterPath());
        Log.d(TAG, "testing this hits");
        Picasso.get()
                .load(posterUrl)
                .resize(100, 100)
                .placeholder(R.color.colorAccent)
                .into(holder.moviePosterIV);
    }

    // gets the number of items in the adapter
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
