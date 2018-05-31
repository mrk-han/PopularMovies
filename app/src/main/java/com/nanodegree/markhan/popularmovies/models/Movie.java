package com.nanodegree.markhan.popularmovies.models;

import com.squareup.moshi.Json;

public class Movie {
    public static final String MOVIE_POSTER_PATH = "http://image.tmdb.org/t/p/w342/";

    @Json(name = "vote_count")
    private int voteCount;
    @Json(name = "id")
    private int id;
    @Json(name = "vote_average")
    private double voteAverage;
    @Json(name = "title")
    private String title;
    @Json(name = "popularity")
    private double popularity;
    @Json(name = "poster_path")
    private String posterPath;
    @Json(name = "backdrop_path")
    private String backdropPath;
    @Json(name = "overview")
    private String overview;
    @Json(name = "release_date")
    private String releaseDate;

    public Movie(){}

    public int getVoteCount() {
        return voteCount;
    }

    public int getId() {
        return id;
    }

    public double getVoteAverage() {
        return voteAverage;
    }

    public String getTitle() {
        return title;
    }

    public double getPopularity() {
        return popularity;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}