package nd.centertableinc.popularmovies1.activity.movie_overview.overview_states;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.utils.movie_db.movie_types.MovieData;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class PopularMovies implements OverviewState{
    public static final int stateId = R.id.item_most_popular;
    int currentPage;

    MovieData movieData;

    public PopularMovies(MovieData movieData)
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
        movieData.requestForMovies(listener, currentPage);
    }

    @Override
    public void requestForMoviesMore(AsyncDataListener listener) {
        currentPage++;
        movieData.requestForMovies(listener, currentPage);
    }

    @Override
    public int getStateId() {
        return stateId;
    }

}
