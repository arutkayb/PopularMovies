package nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates;

import android.content.Context;
import android.util.Log;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.Activity.State;
import nd.centertableinc.popularmovies1.Activity.StateContext;
import nd.centertableinc.popularmovies1.Activity.StateFactory;
import nd.centertableinc.popularmovies1.Activity.UnknownStateError;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class OverviewStateContext implements StateContext <OverviewState> {
    OverviewState currentState;
    OverviewStateFactory stateFactory;
    Context context;
    AsyncDataListener listener;

    public OverviewStateContext(Context context, AsyncDataListener listener, OverviewStateFactory factory)
    {
        stateFactory = factory;
        this.context = context;
        this.listener = listener;

        //Default state is PopularMovies state
        try
        {
            currentState =  stateFactory.getState(OverviewStateFactory.POPULAR_STATE);
        }catch (UnknownStateError er)
        {
            Log.e("OverviewStateContext", "Error initializing the state: " + er.getMessage());
        }
    }

    @Override
    public void setState(int state) throws UnknownStateError
    {
        try {
            currentState.exit();

            currentState = stateFactory.getState(state);
            currentState.enter();
        }catch (UnknownStateError e)
        {
            throw e;
        }
    }

    @Override
    public OverviewState getCurrentState()
    {
        return currentState;
    }

}
