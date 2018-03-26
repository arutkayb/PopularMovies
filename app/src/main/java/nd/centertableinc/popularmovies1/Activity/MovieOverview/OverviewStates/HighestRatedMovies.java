package nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates;

import android.content.Context;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.Data.MovieData;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class HighestRatedMovies implements OverviewState{
    AsyncDataListener listener;

    int stateId;
    int currentPage;

    Context context;
    MovieData movieData;

    public HighestRatedMovies(Context context, AsyncDataListener listener, int id, MovieData movieData)
    {
        stateId = id;
        this.listener = listener;
        this.context = context;
        currentPage = 1;

        this.movieData = movieData;
    }

    @Override
    public void enter() {
    }

    @Override
    public void exit() {

    }

    @Override
    public void requestForMovies() {
        currentPage = 1;
        movieData.requestForMovies(currentPage);
    }

    @Override
    public void requestForMoviesMore() {
        currentPage++;
        movieData.requestForMovies(currentPage);
    }

    @Override
    public int getStateId() {
        return stateId;
    }

    @Override
    public void onDataLoad(String result){
        listener.onDataLoad(result);
    }
}
