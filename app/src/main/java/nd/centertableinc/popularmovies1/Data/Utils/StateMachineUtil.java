package nd.centertableinc.popularmovies1.Data.Utils;

import android.util.Log;

import nd.centertableinc.popularmovies1.Activity.MovieOverview.OverviewStates.OverviewStateFactory;
import nd.centertableinc.popularmovies1.Activity.StateContext;
import nd.centertableinc.popularmovies1.Activity.UnknownStateError;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class StateMachineUtil {
    public static void setState(StateContext stateContext, int state)
    {
        try
        {
            stateContext.setState(state);
        }catch (UnknownStateError e)
        {
            Log.e("StateMachineUtil", "Cannot set state: " + e.getMessage());
        }
    }

}
