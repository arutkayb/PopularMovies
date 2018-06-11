package nd.centertableinc.popularmovies1.activity.movie_details;

import android.content.res.Configuration;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nd.centertableinc.popularmovies1.R;
import nd.centertableinc.popularmovies1.activity.RecyclerViewContainer;
import nd.centertableinc.popularmovies1.adapter.MovieReviewsAdapter;
import nd.centertableinc.popularmovies1.data.movie.MovieReview;
import nd.centertableinc.popularmovies1.data.utils.HttpUtil;

public class MovieReviewsActivity extends AppCompatActivity implements RecyclerViewContainer{
    RecyclerView recyclerView;
    MovieReviewsAdapter movieReviewsAdapter;
    List<MovieReview> movieReviews;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_reviews);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException ex)
        {
            Log.e(getClass().getName(), "Action bar error");
        }

        recyclerView = findViewById(R.id.movie_review_recycler_view);

        movieReviews = getIntent().getParcelableArrayListExtra(MovieReview.PARCELABLE_NAME);

        if(movieReviews != null) {
            movieReviewsAdapter = new MovieReviewsAdapter(this, this, movieReviews);
            setRecyclerViewLayoutManager(getResources().getConfiguration());
            recyclerView.setAdapter(movieReviewsAdapter);
        }else
        {
            finish();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onCustomClickListener(int itemPosition) {
        Uri uri = Uri.parse(movieReviews.get(itemPosition).getUrl());
        HttpUtil.navigateToUri(getBaseContext(), uri);
    }

    @Override
    public void itemHitListener(int itemPosition) {
        //Don't do anything
    }

    private void setRecyclerViewLayoutManager(Configuration config)
    {
        // Checks the orientation of the screen
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        }
    }
}
