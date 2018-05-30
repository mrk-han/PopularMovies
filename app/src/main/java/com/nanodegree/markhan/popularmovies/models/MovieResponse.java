package com.nanodegree.markhan.popularmovies.models;

import java.util.List;
import com.squareup.moshi.Json;

public class MovieResponse {

    @Json(name = "page")
    private int page;
    @Json(name = "total_results")
    private int totalResults;
    @Json(name = "total_pages")
    private int totalPages;
    @Json(name = "results")
    private List<Movie> movies = null;

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<Movie> getMovies() {
        return movies;
    }
}