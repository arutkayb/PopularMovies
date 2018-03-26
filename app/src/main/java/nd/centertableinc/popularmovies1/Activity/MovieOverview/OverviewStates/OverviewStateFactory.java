package nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates;

import android.content.Context;
import android.util.Log;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.Activity.StateFactory;
import nd.centertableinc.popularmovies1.Activity.UnknownStateError;
import nd.centertableinc.popularmovies1.Data.MovieDbHighestRated;
import nd.centertableinc.popularmovies1.Data.MovieDbPopular;
import nd.centertableinc.popularmovies1.Data.MoviesFavorite;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class OverviewStateFactory implements StateFactory{
    AsyncDataListener listener;

    OverviewState popularState;
    OverviewState favoriteState;
    OverviewState highestRatedState;

    public static final int POPULAR_STATE = 0;
    public static final int HIGHEST_RATED_STATE = 1;
    public static final int FAVORITE_STATE = 2;

    Context context;
    public OverviewStateFactory(Context context, AsyncDataListener listener)
    {
        this.listener = listener;
        this.context = context;
    }

    public OverviewState getState(int state) throws UnknownStateError
    {
        OverviewState ret;

        switch (state)
        {
            case POPULAR_STATE:
                if(popularState == null)
                    popularState = new PopularMovies(context, listener, POPULAR_STATE, new MovieDbPopular(context,listener));

                ret = popularState;
                break;
            case HIGHEST_RATED_STATE:
                if(highestRatedState == null)
                    highestRatedState = new HighestRatedMovies(context, listener, HIGHEST_RATED_STATE, new MovieDbHighestRated(context,listener));

                ret = highestRatedState;
                break;
            case FAVORITE_STATE:
                if(favoriteState == null)
                    favoriteState = new FavoriteMovies(context, listener, FAVORITE_STATE, new MoviesFavorite(context, listener));

                ret = favoriteState;
                break;
            default:
                throw new UnknownStateError("Unknown state: " + String.valueOf(state));
        }

        return ret;
    }
}
