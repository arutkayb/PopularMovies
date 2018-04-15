package nd.centertableinc.popularmovies1.Activity;

import nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates.OverviewStateEnum;

/**
 * Created by Rutkay on 25.03.2018.
 */

public interface StateContext <State>{
    State getCurrentState();
    void setState(OverviewStateEnum state);
}
