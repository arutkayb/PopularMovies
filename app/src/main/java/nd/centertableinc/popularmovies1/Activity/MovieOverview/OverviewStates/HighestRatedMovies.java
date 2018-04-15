package nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates;

import android.content.Context;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.Data.MovieData;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class HighestRatedMovies implements OverviewState{
    final int stateId = R.id.item_highest_rated;
    int currentPage;

    MovieData movieData;

    public HighestRatedMovies( MovieData movieData)
    {
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
    public void requestForMovies(AsyncDataListener listener) {
        currentPage = 1;
        movieData.requestForMovies(currentPage,listener);
    }

    @Override
    public void requestForMoviesMore(AsyncDataListener listener) {
        currentPage++;
        movieData.requestForMovies(currentPage,listener);
    }

    @Override
    public int getStateId() {
        return stateId;
    }

}
