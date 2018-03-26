package nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates;

import android.content.Context;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.Data.MovieData;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class PopularMovies implements OverviewState{
    int stateId;
    int currentPage;

    MovieData movieData;

    public PopularMovies(int id, MovieData movieData)
    {
        stateId = id;
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

}
