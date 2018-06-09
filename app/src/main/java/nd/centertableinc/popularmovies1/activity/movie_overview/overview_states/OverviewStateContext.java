package nd.centertableinc.popularmovies1.activity.movie_overview.overview_states;

import android.content.Context;

import nd.centertableinc.popularmovies1.activity.StateContext;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class OverviewStateContext implements StateContext <OverviewState> {
    OverviewState currentState;
    OverviewStateFactory stateFactory;
    Context context;

    public OverviewStateContext(Context context, OverviewStateFactory factory)
    {
        stateFactory = factory;
        this.context = context;

        //Default state is PopularMovies state
        currentState =  stateFactory.getState(OverviewStateEnum.POPULAR_STATE);
    }

    @Override
    public void setState(OverviewStateEnum state)
    {
        currentState.exit();

        currentState = stateFactory.getState(state);
        currentState.enter();
    }

    @Override
    public OverviewState getCurrentState()
    {
        return currentState;
    }

}
