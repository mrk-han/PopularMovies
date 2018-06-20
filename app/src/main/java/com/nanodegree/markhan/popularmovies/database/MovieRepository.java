package com.nanodegree.markhan.popularmovies.database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class MovieRepository {

    private MovieDao movieDao;
    private LiveData<List<MovieEntity>> allMovies;

    MovieRepository(Application application) {
        MovieRoomDatabase db = MovieRoomDatabase.getDatabase(application);
        movieDao = db.movieDao();
        allMovies = movieDao.getAllMovies();
    }

    LiveData<List<MovieEntity>> getAllMovies() {
        return allMovies;
    }

    public void insert(MovieEntity movieEntity) {
        new insertAsyncTask(movieDao).execute(movieEntity);
    }

    private static class insertAsyncTask extends AsyncTask<MovieEntity, Void, Void> {
        private MovieDao asyncTaskDao;

        insertAsyncTask(MovieDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MovieEntity... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
