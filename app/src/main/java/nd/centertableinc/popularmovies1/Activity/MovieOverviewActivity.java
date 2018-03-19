package nd.centertableinc.popularmovies1.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.List;

import nd.centertableinc.popularmovies1.Adapter.MovieOverviewAdapter;
import nd.centertableinc.popularmovies1.Data.Utils.JsonUtil;
import nd.centertableinc.popularmovies1.Interfaces.AsyncDataListener;
import nd.centertableinc.popularmovies1.Interfaces.RecyclerViewContainer;
import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;
import nd.centertableinc.popularmovies1.Data.MovieDb;
import nd.centertableinc.popularmovies1.Data.Utils.MovieItemUtil;
import nd.centertableinc.popularmovies1.R;

public class MovieOverviewActivity extends AppCompatActivity implements RecyclerViewContainer,AsyncDataListener<String> {
    public List<MovieItem> movieItems;
    private MovieDb movieDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieDb = new MovieDb(this, this, getResources().getString(R.string.api_key));

        requestTheMostPopularMovies();
    }

    private void createMovieCards(JSONObject movieDbJson)
    {
        movieItems = MovieItemUtil.getMovieItemsFromMovieDbJson(movieDbJson);

        MovieOverviewAdapter movieOverviewAdapter = new MovieOverviewAdapter(this, this, movieItems);
        RecyclerView recyclerView = findViewById(R.id.movie_overview_recycler_view);

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

    public void requestTheMostPopularMovies()
    {
        movieDb.requestForTheMostPopularMovies();
    }

    public void requestTheHighestRatedMovies()
    {
        movieDb.requestForTheHighestRatedMovies();
    }

    @Override
    public void onDataLoad(String result){
        JSONObject res = JsonUtil.createJsonObjFromJsonString(result);

        if(res != null)
            createMovieCards(res);
    }

}
