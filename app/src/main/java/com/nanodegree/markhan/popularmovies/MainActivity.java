package com.nanodegree.markhan.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nanodegree.markhan.popularmovies.api.MovieDbService;
import com.nanodegree.markhan.popularmovies.models.Movie;
import com.nanodegree.markhan.popularmovies.models.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BASE_URL = "http://api.themoviedb.org/";
    private final static String API_KEY = BuildConfig.API_KEY;

    @BindView(R.id.movie_thumbail_recyclerview)
    RecyclerView movieRecyclerView;
    RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        movieRecyclerView.setAdapter(new MovieAdapter(this));
        movieRecyclerView.setHasFixedSize(true); // may improve performance depending on size of recyclerview
        movieRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        fetchMovies();
    }

// Should I return void or Observable<MovieResponse> here?
    public void fetchMovies() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        MovieDbService service = retrofit.create(MovieDbService.class);
        final Observable<MovieResponse> call = service.getMovies("popular", API_KEY);
        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieResponse>() {
                    @Override
                    public void onCompleted() {
                        // Nothing to do here
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException)
                        {
                            ((HttpException) e).code();
                            Log.e(TAG, e.toString());
                        }
                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        // Called once the MovieResponse Object is available
                        List<Movie> results = movieResponse.getMovies();
                        movies.add((Movie) results);
                    }
                });

        //NOT SURE WHAT TO RETURN HERE;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItemSelected = item.getItemId();

        if (menuItemSelected == R.id.item_popular_movies) {
            // Get the popular movies using rxjava/retrofit/moshi
        } else if (menuItemSelected == R.id.item_top_rated_movies) {
            // Get the top rated movies using rxjava/retrofit/moshi
        }
        return super.onOptionsItemSelected(item);
    }



}
