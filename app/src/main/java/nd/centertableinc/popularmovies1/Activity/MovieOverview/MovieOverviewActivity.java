package nd.centertableinc.popularmovies1.Activity.MovieOverview;

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

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nd.centertableinc.popularmovies1.Activity.MovieDetails.MovieDetailsActivity;
import nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates.OverviewState;
import nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates.OverviewStateContext;
import nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates.OverviewStateFactory;
import nd.centertableinc.popularmovies1.Activity.State;
import nd.centertableinc.popularmovies1.Activity.StateContext;
import nd.centertableinc.popularmovies1.Activity.UnknownStateError;
import nd.centertableinc.popularmovies1.Adapter.MovieOverviewAdapter;
import nd.centertableinc.popularmovies1.Data.Utils.JsonUtil;
import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.Activity.RecyclerViewContainer;
import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;
import nd.centertableinc.popularmovies1.Data.MovieDb;
import nd.centertableinc.popularmovies1.Data.Utils.MovieItemUtil;
import nd.centertableinc.popularmovies1.Data.Utils.StateMachineUtil;
import nd.centertableinc.popularmovies1.R;

public class MovieOverviewActivity extends AppCompatActivity implements RecyclerViewContainer,AsyncDataListener<String> {
    public List<MovieItem> movieItems;
    RecyclerView recyclerView;
    MovieOverviewAdapter movieOverviewAdapter;

    OverviewStateContext overviewStateContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieItems = new ArrayList<>();

        recyclerView = findViewById(R.id.movie_overview_recycler_view);
        movieOverviewAdapter = new MovieOverviewAdapter(this, this, movieItems);

        setRecyclerViewLayoutManager(getResources().getConfiguration());

        OverviewStateFactory overviewStateFactory = new OverviewStateFactory(this,this);
        overviewStateContext = new OverviewStateContext(this, this, overviewStateFactory);

        StateMachineUtil.setState(overviewStateContext, OverviewStateFactory.POPULAR_STATE);

        overviewStateContext.getCurrentState().requestForMovies();
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

    private void createMovieCards(JSONObject movieDbJson)
    {
        if(movieItems == null)
            movieItems = new ArrayList<>();

        List<MovieItem> tempMovieItems = MovieItemUtil.getMovieItemsFromMovieDbJson(movieDbJson);

        for(int i = 0; i<tempMovieItems.size(); i++)
            movieItems.add(tempMovieItems.get(i));

        movieOverviewAdapter.setMovieItems(movieItems);
        recyclerView.setAdapter(movieOverviewAdapter);

        recyclerView.scrollToPosition(movieItems.size() - tempMovieItems.size() - 1);
    }

    @Override
    public void onCustomClickListener(int itemPosition)
    {
        if(movieItems != null)
            MovieItemUtil.setSelectedMovieItem(movieItems.get(itemPosition));

        Intent intent = new Intent(this, MovieDetailsActivity.class);
        startActivity(intent);
    }

    @Override
    public void onDataLoad(String result){
        JSONObject res = JsonUtil.createJsonObjFromJsonString(result);

        if(res != null)
            createMovieCards(res);
    }


    @Override
    public void itemHitListener(int itemPosition)
    {
        overviewStateContext.getCurrentState().requestForMoviesMore();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.appbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int currentStateId = overviewStateContext.getCurrentState().getStateId();

        switch (item.getItemId()) {
            case R.id.item_most_popular:
                if(currentStateId != OverviewStateFactory.POPULAR_STATE) {
                    movieStateChanged(OverviewStateFactory.POPULAR_STATE);
                }
                break;
            case R.id.item_highest_rated:
                if(currentStateId != OverviewStateFactory.HIGHEST_RATED_STATE) {
                    movieStateChanged(OverviewStateFactory.HIGHEST_RATED_STATE);
                }
                break;
            case R.id.item_favorites:
                if(currentStateId != OverviewStateFactory.FAVORITE_STATE) {
                    movieStateChanged(OverviewStateFactory.FAVORITE_STATE);
                }
                break;
            case R.id.item_refresh:
                clearMovies();
                StateMachineUtil.setState(overviewStateContext, overviewStateContext.getCurrentState().getStateId());
                overviewStateContext.getCurrentState().requestForMovies();
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

    private void movieStateChanged(int state)
    {
        clearMovies();
        StateMachineUtil.setState(overviewStateContext, state);
        overviewStateContext.getCurrentState().requestForMovies();
    }

}
