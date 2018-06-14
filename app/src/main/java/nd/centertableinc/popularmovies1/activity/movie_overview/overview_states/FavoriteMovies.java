package nd.centertableinc.popularmovies1.activity.movie_overview.overview_states;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.utils.movie_db.movie_types.MovieData;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class FavoriteMovies implements OverviewState{
    public static final int stateId = R.id.item_favorites;

    MovieData movieData;

    public FavoriteMovies(MovieData movieData)
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
        //ignore the page and get all the movies for favorite movies
        movieData.requestForMovies(listener, 1);
    }

    @Override
    public int getStateId() {
        return stateId;
    }

}
