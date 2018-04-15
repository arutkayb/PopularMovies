package nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates;

import android.content.Context;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.Data.MovieData;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class FavoriteMovies implements OverviewState{
    private static final int MOVIES_PER_PAGE = 20;

    final int stateId = R.id.item_favorites;
    int currentPage;

    MovieData movieData;

    public FavoriteMovies(MovieData movieData)
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

    }

    @Override
    public void requestForMoviesMore(AsyncDataListener listener) {

    }

    @Override
    public int getStateId() {
        return stateId;
    }

}
