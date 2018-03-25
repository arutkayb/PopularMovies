package nd.centertableinc.popularmovies1.Activity.MovieDetails;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;
import nd.centertableinc.popularmovies1.Data.Utils.MovieItemUtil;
import nd.centertableinc.popularmovies1.R;

public class MovieDetailsActivity extends AppCompatActivity {
    ImageView backdrop;
    TextView title;
    TextView voteAverage;
    TextView popularity;

    TextView origLanguage;
    TextView origTitle;
    TextView isAdult;
    TextView overview;
    TextView releaseDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        backdrop = findViewById(R.id.backdrop_image_view);
        title = findViewById(R.id.title_text_view);
        voteAverage = findViewById(R.id.vote_average_text_view);
        popularity = findViewById(R.id.popularity_text_view);
        origLanguage = findViewById(R.id.orig_language_text_view);
        origTitle = findViewById(R.id.orig_title_text_view);
        isAdult = findViewById(R.id.is_adult_text_view);
        overview = findViewById(R.id.overview_text_view);
        releaseDate = findViewById(R.id.release_date_text_view);

        MovieItem selectedMovie = getIntent().getParcelableExtra(MovieItem.PARCELABLE_NAME);

        if(selectedMovie != null)
            bindHolder(selectedMovie);
        else
            finish();
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
        String imgUrlStr = MovieItemUtil.getLargeImageUrlFromImagePath(movieItem.getBackdropPath());
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
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
