package com.nanodegree.markhan.popularmovies.api;

import android.util.Log;

import com.nanodegree.markhan.popularmovies.models.MovieResponse;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface MovieDbService {
    @GET("/3/movie/{category}")
    Observable<MovieResponse> getMovies (
            @Path("category") String category,
            @Query("api_key") String key);
}