package nd.centertableinc.popularmovies1.activity.movie_details;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.movie.MovieItem;
import nd.centertableinc.popularmovies1.data.movie.MovieReview;
import nd.centertableinc.popularmovies1.data.movie.MovieTrailer;
import nd.centertableinc.popularmovies1.data.utils.HttpUtil;
import nd.centertableinc.popularmovies1.data.utils.MovieUtil;
import nd.centertableinc.popularmovies1.data.utils.movie_db.MovieDBReviews;
import nd.centertableinc.popularmovies1.data.utils.movie_db.MovieDBTrailers;
import nd.centertableinc.popularmovies1.data.utils.movie_db.movie_types.MoviesDbFavorite;
import nd.centertableinc.popularmovies1.data.utils.provider_utils.MoviesFavoriteUtil;
import nd.centertableinc.popularmovies1.R;

public class MovieDetailsActivity extends AppCompatActivity implements AsyncDataListener{
    ImageView favorite;

    ImageView backdrop;
    TextView title;
    TextView voteAverage;
    TextView popularity;
    TextView origLanguage;
    TextView origTitle;
    TextView isAdult;
    TextView overview;
    TextView releaseDate;

    Button button_trailer;
    Button button_reviews;

    MovieItem selectedMovie;

    MovieDBReviews movieDBReviews;
    MovieDBTrailers movieDBTrailers;

    boolean isFavorite = false;

    List<MovieReview> reviews;
    List<MovieTrailer> trailers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        try {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }catch (NullPointerException ex)
        {
            Log.e(getClass().getName(), "Action bar error");
        }

        initialize();

        if(selectedMovie != null)
            bindHolder(selectedMovie);
        else
            finish();
    }

    private void initialize(){
        reviews = new ArrayList<>();
        trailers = new ArrayList<>();

        selectedMovie = getIntent().getParcelableExtra(MovieItem.PARCELABLE_NAME);

        initializeViews();

        String movieId = String.valueOf(selectedMovie.getId());

        movieDBReviews = new MovieDBReviews(this);
        movieDBReviews.requestForMovieReviews(this, movieId);

        movieDBTrailers = new MovieDBTrailers(this);
        movieDBTrailers.requestForMovieVideos(this, movieId);
    }

    private void initializeViews(){

        backdrop = findViewById(R.id.backdrop_image_view);
        title = findViewById(R.id.title_text_view);
        voteAverage = findViewById(R.id.vote_average_text_view);
        popularity = findViewById(R.id.popularity_text_view);
        origLanguage = findViewById(R.id.orig_language_text_view);
        origTitle = findViewById(R.id.orig_title_text_view);
        isAdult = findViewById(R.id.is_adult_text_view);
        overview = findViewById(R.id.overview_text_view);
        releaseDate = findViewById(R.id.release_date_text_view);

        favorite = findViewById(R.id.is_favorite_image_view);
        favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteButtonClicked();
            }
        });

        button_reviews = findViewById(R.id.button_reviews);
        button_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userReviewsOnClick(reviews);
            }
        });
        button_reviews.setEnabled(false);

        button_trailer = findViewById(R.id.button_trailer);
        button_trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(trailers.size()>0) {
                    Uri uri = null;

                    for(int i = 0; i < trailers.size(); i++) {
                        //find the first youtube trailer and stop to navigate to there
                        if( trailers.get(i).getSite().toLowerCase().contains("youtube")) {
                            uri = getYoutubeSpecificTrailerUri(trailers.get(i));
                            break;
                        }
                    }

                    HttpUtil.navigateToUri(getBaseContext(), uri);
                }
            }
        });
        button_trailer.setEnabled(false);
    }

    private Uri getYoutubeSpecificTrailerUri(MovieTrailer trailer){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(trailer.getSite().toLowerCase() + ".com")
                .appendPath("watch")
                .appendQueryParameter("v",trailer.getKey());

        return builder.build();
    }

    public void bindHolder(MovieItem movieItem)
    {
        String titleStr = getString(R.string.title) + ": " + movieItem.getTitle();
        title.setText(titleStr);

        String voteAverageStr = getString(R.string.vote_average) + ": " + String.valueOf(movieItem.getVoteAverage());
        voteAverage.setText(voteAverageStr);

        String popularityString = getString(R.string.popularity) + ": " + String.valueOf((int)movieItem.getPopularity());
        popularity.setText(popularityString);

        backdrop.setContentDescription(titleStr);
        String imgUrlStr = MovieUtil.getLargeImageUrlFromImagePath(movieItem.getBackdropPath());
        Picasso.with(this).load(imgUrlStr).into(backdrop);

        String origLangStr = getString(R.string.orig_language) + ": " + movieItem.getOrigLanguage();
        origLanguage.setText(origLangStr);

        String origTitleStr = getString(R.string.orig_title) + ": " + movieItem.getOrigTitle();
        origTitle.setText(origTitleStr);

        String adultStr = getString(R.string.adult) + ": ";
        if(movieItem.isAdult())
            adultStr = adultStr + getString(R.string.yes);
        else
            adultStr = adultStr + getString(R.string.no);

        isAdult.setText(adultStr);

        String overviewStr = getString(R.string.overview) + ": " + movieItem.getOverview();
        overview.setText(overviewStr);

        String releaseDateStr = getString(R.string.release_date) + ": " + movieItem.getReleaseDate();
        releaseDate.setText(releaseDateStr);

        isFavorite = MoviesFavoriteUtil.isMovieAlreadyFavorite(getApplicationContext(), selectedMovie);
        drawFavoriteIcon();
    }


    // Do this as an async process
    private void drawFavoriteIcon()
    {
        Drawable favoriteIcon = getFavoriteDrawable(isFavorite);
        favorite.setImageDrawable(favoriteIcon);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void favoriteButtonClicked()
    {
        isFavorite = !isFavorite;

        drawFavoriteIcon();

        if(isFavorite)
        {
            MoviesFavoriteUtil.addMovieToFavorites(getApplicationContext(), selectedMovie);
        }
        else
        {
            MoviesFavoriteUtil.removeMoviteFromFavorites(getApplicationContext(), selectedMovie);
        }
    }

    private Drawable getFavoriteDrawable(boolean isFavorite)
    {
        Drawable favoriteIcon;

        if(isFavorite)
            favoriteIcon = getResources().getDrawable(R.drawable.ic_favorites_red_24dp);
        else
            favoriteIcon = getResources().getDrawable(R.drawable.ic_favorites_border_24dp);

        return favoriteIcon;
    }

    @Override
    public void onDataLoad(Object result){
        //result may be a MovieReview or a MovieTrailer
        //TODO: refactor here to not do casting or something better

        List<MovieReview> reviews = null;
        List<MovieTrailer> trailers = null;

        try {
            if (((List) result).get(0) instanceof MovieReview) {
                reviews = (List<MovieReview>) result;
                if (reviews.size() > 0) {
                    this.reviews = reviews;
                    button_reviews.setEnabled(true);
                }
            } else if (((List) result).get(0) instanceof MovieTrailer) {
                trailers = (List<MovieTrailer>) result;

                if (trailers.size() > 0) {
                    this.trailers = trailers;
                    button_trailer.setEnabled(true);
                }
            }
        }catch (ClassCastException ex){
            Log.e(getClass().getName(), "onDataLoad, Cast exception: " + ex.toString());
        }catch (Exception ex){
            Log.e(getClass().getName(), "onDataLoad, Exception: " + ex.toString());
        }
    }

    private void userReviewsOnClick(List<MovieReview> movieReviews){
        Intent intent = new Intent(this, MovieReviewsActivity.class);

        intent.putParcelableArrayListExtra(MovieReview.PARCELABLE_NAME, (ArrayList<? extends Parcelable>) movieReviews);

        startActivity(intent);
    }
}
