package nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates;

import android.content.Context;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class FavoriteMovies implements OverviewState{
    private static final int MOVIES_PER_PAGE = 20;

    AsyncDataListener listener;
    int stateId;
    int currentPage;

    Context context;

    public FavoriteMovies(Context context, AsyncDataListener listener, int id)
    {
        stateId = id;
        this.listener = listener;
        this.context = context;
        currentPage = 1;
    }

    @Override
    public void enter() {
    }

    @Override
    public void exit() {

    }

    @Override
    public void requestForMovies() {

    }

    @Override
    public void requestForMoviesMore() {

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
