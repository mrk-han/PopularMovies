package com.nanodegree.markhan.popularmovies.database;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.nanodegree.markhan.popularmovies.database.MovieEntity;
import com.nanodegree.markhan.popularmovies.database.MovieRepository;

import java.util.List;

public class MovieViewModel extends AndroidViewModel{

    private MovieRepository movieRepository;
    private LiveData<List<MovieEntity>> allWords;


    public MovieViewModel(Application application) {
        super(application);
        movieRepository = new MovieRepository(application);
        allWords = movieRepository.getAllMovies();
    }

    LiveData<List<MovieEntity>> getAllWords() {
        return allWords;
    }

    public void insert(MovieEntity movieEntity) {
        movieRepository.insert(movieEntity);
    }
}
