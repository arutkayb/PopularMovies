package nd.centertableinc.popularmovies1.activity.movie_overview.overview_states;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.utils.movie_db.movie_types.MovieData;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class HighestRatedMovies implements OverviewState{
    public static final int stateId = R.id.item_highest_rated;

    MovieData movieData;

    public HighestRatedMovies( MovieData movieData)
    {
        this.movieData = movieData;
    }

    @Override
    public void enter() {
    }

    @Override
    public void exit() {

    }

    @Override
    public void requestForMovies(AsyncDataListener listener, int page) {
        movieData.requestForMovies(listener, page);
    }

    @Override
    public int getStateId() {
        return stateId;
    }

}
