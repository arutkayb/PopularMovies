package nd.centertableinc.popularmovies1.activity.movie_overview;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.OverScroller;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nd.centertableinc.popularmovies1.activity.movie_details.MovieDetailsActivity;
import nd.centertableinc.popularmovies1.activity.movie_overview.overview_states.FavoriteMovies;
import nd.centertableinc.popularmovies1.activity.movie_overview.overview_states.OverviewState;
import nd.centertableinc.popularmovies1.activity.movie_overview.overview_states.OverviewStateContext;
import nd.centertableinc.popularmovies1.activity.movie_overview.overview_states.OverviewStateEnum;
import nd.centertableinc.popularmovies1.activity.movie_overview.overview_states.OverviewStateFactory;
import nd.centertableinc.popularmovies1.adapter.MovieOverviewAdapter;
import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.activity.RecyclerViewContainer;
import nd.centertableinc.popularmovies1.data.recycler_view_items.MovieItem;
import nd.centertableinc.popularmovies1.R;

public class MovieOverviewActivity extends AppCompatActivity implements RecyclerViewContainer,AsyncDataListener<List<MovieItem>> {
    public List<MovieItem> movieItems;
    RecyclerView recyclerView;
    MovieOverviewAdapter movieOverviewAdapter;

    OverviewStateContext overviewStateContext;

    TextView movie_overview_text_nothing_to_load;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movie_overview_text_nothing_to_load = findViewById(R.id.movie_overview_text_nothing_to_load);

        movieItems = new ArrayList<>();

        recyclerView = findViewById(R.id.movie_overview_recycler_view);
        movieOverviewAdapter = new MovieOverviewAdapter(this, this, movieItems);

        setRecyclerViewLayoutManager(getResources().getConfiguration());

        OverviewStateFactory overviewStateFactory = new OverviewStateFactory(this);
        overviewStateContext = new OverviewStateContext(this, overviewStateFactory);

        overviewStateContext.setState(OverviewStateEnum.HIGHEST_RATED_STATE);

        refreshMovies();
    }

    @Override
    protected void onResume() {
        //If any change is occured on the FavoriteMovies like adding or removing, it should be refreshed
        if(overviewStateContext.getCurrentState().getStateId() == FavoriteMovies.stateId)
            refreshMovies();

        super.onResume();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        setRecyclerViewLayoutManager(newConfig);
    }

    private void setRecyclerViewLayoutManager(Configuration config)
    {
        // Checks the orientation of the screen
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        } else if (config.orientation == Configuration.ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        }
    }

    private void createMovieCards(List<MovieItem> items)
    {
        if(movieItems == null)
            movieItems = new ArrayList<>();

        for(int i = 0; i<items.size(); i++)
            movieItems.add(items.get(i));

        movieOverviewAdapter.setMovieItems(movieItems);
        recyclerView.setAdapter(movieOverviewAdapter);

        recyclerView.scrollToPosition(movieItems.size() - items.size() - 1);

        if(movieItems.size()>0)
            movie_overview_text_nothing_to_load.setText("");
        else
            movie_overview_text_nothing_to_load.setText(getString(R.string.nothing_to_load));
    }

    @Override
    public void onCustomClickListener(int itemPosition)
    {
        if(movieItems != null) {
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtra(MovieItem.PARCELABLE_NAME, movieItems.get(itemPosition));
            startActivity(intent);
        }
        else
        {
            Log.e("MoviewOverviewActivity", "Cannot start activity, movieItems is null");
        }
    }

    @Override
    public void onDataLoad(List<MovieItem> result){
        createMovieCards(result);
    }


    @Override
    public void itemHitListener(int itemPosition)
    {
        overviewStateContext.getCurrentState().requestForMoviesMore(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movies_overview_bar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item_most_popular:
            case R.id.item_highest_rated:
            case R.id.item_favorites:
                loadMovies(item);
                break;
            case R.id.item_refresh:
                refreshMovies();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void clearMovies()
    {
        movieItems.clear();
        movieOverviewAdapter.setMovieItems(movieItems);
        recyclerView.setAdapter(movieOverviewAdapter);
    }

    private void refreshMovies(){
        clearMovies();
        OverviewStateEnum state = OverviewStateEnum.getById(overviewStateContext.getCurrentState().getStateId());
        overviewStateContext.setState(state);
        overviewStateContext.getCurrentState().requestForMovies(this);
    }

    private void loadMovies(MenuItem item){
        clearMovies();
        OverviewStateEnum state = OverviewStateEnum.getById(item.getItemId());
        overviewStateContext.setState(state);
        overviewStateContext.getCurrentState().requestForMovies(this);
    }
}
