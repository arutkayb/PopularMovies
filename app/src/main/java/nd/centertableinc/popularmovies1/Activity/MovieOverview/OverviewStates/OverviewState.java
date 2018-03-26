package nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.Activity.State;

/**
 * Created by Rutkay on 25.03.2018.
 */

public interface OverviewState extends State{
    void enter();
    void exit();
    void requestForMovies();
    void requestForMoviesMore();
}
