package nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates;

import android.content.Context;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.Data.MovieDb;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class PopularMovies implements OverviewState{
    AsyncDataListener listener;

    int stateId;
    int currentPage;

    Context context;
    MovieDb movieDb;

    public PopularMovies(Context context, AsyncDataListener listener, int id)
    {
        stateId = id;
        this.listener = listener;
        this.context = context;
        currentPage = 1;

        movieDb = new MovieDb(context, listener);
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
        movieDb.requestForTheMostPopularMovies(currentPage);
    }

    @Override
    public void requestForMoviesMore() {
        currentPage++;
        movieDb.requestForTheMostPopularMovies(currentPage);
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
