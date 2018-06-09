package nd.centertableinc.popularmovies1.activity;

import nd.centertableinc.popularmovies1.activity.movie_overview.overview_states.OverviewStateEnum;

/**
 * Created by Rutkay on 25.03.2018.
 */

public interface StateFactory{
    State getState(OverviewStateEnum state);
}
