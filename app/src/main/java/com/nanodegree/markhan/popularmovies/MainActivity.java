package com.nanodegree.markhan.popularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.nanodegree.markhan.popularmovies.api.MovieDbService;
import com.nanodegree.markhan.popularmovies.models.Movie;
import com.nanodegree.markhan.popularmovies.models.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
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
        createRetrofit();
        movies = ;
        // Create adapter passing in the movie data
        MovieAdapter adapter = new MovieAdapter(movies);


        // Attach the adapter to the recyclerview to populate items
        movieRecyclerView.setAdapter(adapter);
        movieRecyclerView.setHasFixedSize(true); // may improve performance depending on size of recyclerview
        movieRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    public void createRetrofit() {
        // Retrofit setup with Moshi Converter
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build();

        MovieDbService service = retrofit.create(MovieDbService.class);

        Observable<MovieResponse> call = service.getMovies("popular", API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MovieResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(MovieResponse movieResponse) {

                    }
                })

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
