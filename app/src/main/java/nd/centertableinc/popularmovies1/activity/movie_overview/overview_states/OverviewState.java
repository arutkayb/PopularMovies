package nd.centertableinc.popularmovies1.activity.movie_overview.overview_states;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.activity.State;

/**
 * Created by Rutkay on 25.03.2018.
 */

public interface OverviewState extends State{
    void enter();
    void exit();
    void requestForMovies(AsyncDataListener listener);
    void requestForMoviesMore(AsyncDataListener listener);
}
