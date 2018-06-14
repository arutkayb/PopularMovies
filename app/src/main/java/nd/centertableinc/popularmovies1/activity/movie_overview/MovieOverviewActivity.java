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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nd.centertableinc.popularmovies1.activity.movie_details.MovieDetailsActivity;
import nd.centertableinc.popularmovies1.activity.movie_overview.overview_states.FavoriteMovies;
import nd.centertableinc.popularmovies1.activity.movie_overview.overview_states.OverviewStateContext;
import nd.centertableinc.popularmovies1.activity.movie_overview.overview_states.OverviewStateEnum;
import nd.centertableinc.popularmovies1.activity.movie_overview.overview_states.OverviewStateFactory;
import nd.centertableinc.popularmovies1.adapter.MovieOverviewAdapter;
import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.activity.RecyclerViewContainer;
import nd.centertableinc.popularmovies1.data.movie.MovieItem;
import nd.centertableinc.popularmovies1.R;

public class MovieOverviewActivity extends AppCompatActivity implements RecyclerViewContainer,AsyncDataListener<List<MovieItem>> {
    public List<MovieItem> movieItems;
    RecyclerView recyclerView;
    MovieOverviewAdapter movieOverviewAdapter;

    OverviewStateContext overviewStateContext;

    TextView movie_overview_text_nothing_to_load;

    private int lastRequestedPage = 0;
    private int lastReturnedPage = 0;
    private int persistedScrollPosition = 0;
    private int itemsPerLinesInGrid = 0;

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
        persistedScrollPosition = getFirstVisibleItemOfRecyclerView();
        setRecyclerViewLayoutManager(newConfig);
        setScrollToPosition(persistedScrollPosition);
    }

    private void setRecyclerViewLayoutManager(Configuration config)
    {
        // Checks the orientation of the screen
        if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            itemsPerLinesInGrid = 4;
        } else{
            itemsPerLinesInGrid = 2;
        }

        recyclerView.setLayoutManager(new GridLayoutManager(this, itemsPerLinesInGrid));
    }

    private void createMovieCards(List<MovieItem> items)
    {
        if(items == null)
            return;

        if(movieItems == null)
            movieItems = new ArrayList<>();

        for(int i = 0; i<items.size(); i++)
            movieItems.add(items.get(i));

        lastReturnedPage++;

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
        if(movieItems != null){
            Intent intent = new Intent(this, MovieDetailsActivity.class);
            intent.putExtra(MovieItem.PARCELABLE_NAME, movieItems.get(itemPosition));
            startActivity(intent);
        }
        else{
            Log.e("MovieOverviewActivity", "Cannot start activity, movieItems is null");
        }
    }

    @Override
    public void onDataLoad(List<MovieItem> result){
        createMovieCards(result);
    }

    @Override
    public void itemHitListener(int itemPosition)
    {
        if((lastRequestedPage == lastReturnedPage) && !isScrollOnTop()) {
            lastRequestedPage++;
            overviewStateContext.getCurrentState().requestForMovies(this, lastRequestedPage);
        }
    }

    private boolean isScrollOnTop()
    {
        // TODO: A little bit nasty here, please find a better solution
        boolean res = (getFirstVisibleItemOfRecyclerView() < itemsPerLinesInGrid * 2);
        return res;
    }

    private int getFirstVisibleItemOfRecyclerView(){
        return ((GridLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
    }

    private void setScrollToPosition(int position){
        if(position <= 0)
            position = 0;

        recyclerView.scrollToPosition(position);
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
                clearMovies();
                loadMoviesForState(OverviewStateEnum.getById(item.getItemId()));
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
        clearPageInfo();
        movieItems.clear();
        movieOverviewAdapter.setMovieItems(movieItems);
        recyclerView.setAdapter(movieOverviewAdapter);
    }

    private void loadMoviesForState(OverviewStateEnum state){
        overviewStateContext.setState(state);
        loadMovies();
    }

    private void loadMovies(){
        lastRequestedPage++;
        overviewStateContext.getCurrentState().requestForMovies(this, lastRequestedPage);
    }

    private void clearPageInfo(){
        lastRequestedPage = 0;
        lastReturnedPage = 0;
    }

    private void refreshMovies(){
        clearMovies();
        loadMovies();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String text = movie_overview_text_nothing_to_load.getText().toString();
        outState.putString("movie_overview_text_nothing_to_load", text);
        persistedScrollPosition = getFirstVisibleItemOfRecyclerView();
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedState) {
        super.onRestoreInstanceState(savedState);
        String text = savedState.getString("movie_overview_text_nothing_to_load");
        movie_overview_text_nothing_to_load.setText(text);
        setScrollToPosition(persistedScrollPosition);
    }
}
