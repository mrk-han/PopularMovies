package com.nanodegree.markhan.popularmovies.Model;

public class Movie {

    // Variables
    private String movieTitle;
    private String movieDetails;
    private String movieReleaseDate;
    private double movieRating;

    public String getMovieTitle() {
        return movieTitle;
    }

    public String getMovieDetails() {
        return movieDetails;
    }

    public String getMovieReleaseDate() {
        return movieReleaseDate;
    }

    public double getMovieRating() {
        return movieRating;
    }

    public Movie(String movieTitle, String movieDetails, String movieReleaseDate, double movieRating) {
        this.movieTitle = movieTitle;
        this.movieDetails = movieDetails;
        this.movieReleaseDate = movieReleaseDate;
        this.movieRating = movieRating;
    }


}
