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
import com.nanodegree.markhan.popularmovies.api.RetrofitClient;
import com.nanodegree.markhan.popularmovies.models.Movie;
import com.nanodegree.markhan.popularmovies.models.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String BASE_URL = "http://api.themoviedb.org/";
    private final static String API_KEY = BuildConfig.API_KEY;
    List<Movie> testMovies = null;

    @BindView(R.id.movie_thumbnail_recyclerview)
    RecyclerView movieRecyclerView;
    RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    MovieAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapter = new MovieAdapter(this, testMovies);
        movieRecyclerView.setHasFixedSize(true);
        movieRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        movieRecyclerView.setAdapter(adapter);
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie());
        adapter.setMovieList(movies);


    }

    // Should I return void or Observable<MovieResponse> here?
    public MovieResponse fetchMovies(String category, String API_KEY) {
        // get singleton instance of Retrofit
        Retrofit retrofit = RetrofitClient.getClient(BASE_URL, rxAdapter);

        final MovieDbService service = retrofit.create(MovieDbService.class);


        Observable<MovieResponse> call = service.getMovies(category, API_KEY);

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieResponse>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (e instanceof HttpException) {
                            ((HttpException) e).code();
                            Log.e(TAG, e.toString());
                        }
                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {
                        System.out.println("onNext: " + movieResponse);
                        movieResponse.getMovies().toArray();
                        Log.d(TAG, String.valueOf(movieResponse));
                        List<Movie> testMovies = movieResponse.getMovies();
                        movieRecyclerView.setAdapter(new MovieAdapter(MainActivity.this, testMovies));
                        Log.d(TAG, "Number of movies: " + testMovies.size());
                    }
                });
        return null;
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
            fetchMovies(this.getResources().getString(R.string.title_popular), API_KEY);
        } else if (menuItemSelected == R.id.item_top_rated_movies) {
            fetchMovies(this.getResources().getString(R.string.title_top_rated), API_KEY);
        }
        return super.onOptionsItemSelected(item);
    }


}
