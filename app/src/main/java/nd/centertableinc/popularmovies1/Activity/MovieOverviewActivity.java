package nd.centertableinc.popularmovies1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import nd.centertableinc.popularmovies1.Adapter.MovieOverviewAdapter;
import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;
import nd.centertableinc.popularmovies1.Data.Utils.MovieDbUtil;
import nd.centertableinc.popularmovies1.Data.Utils.MovieItemUtil;
import nd.centertableinc.popularmovies1.R;

public class MovieOverviewActivity extends AppCompatActivity implements RecyclerViewContainer{
    public static List<MovieItem> movieItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieItems = MovieItemUtil.getMovieItemsFromMovieDbJson(MovieDbUtil.getTheHighestRatedMoviesJson());

        final MovieOverviewAdapter movieOverviewAdapter = new MovieOverviewAdapter(this, movieItems);
        final RecyclerView recyclerView = findViewById(R.id.movie_overview_recycler_view);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(movieOverviewAdapter);
    }

    @Override
    public void onCustomClickListener(int itemPosition)
    {
        //TODO: start MovieDetailsActivity here
        if(movieItems != null)
            Toast.makeText(this, movieItems.get(itemPosition).getOrigTitle(), Toast.LENGTH_SHORT).show();
    }
}
