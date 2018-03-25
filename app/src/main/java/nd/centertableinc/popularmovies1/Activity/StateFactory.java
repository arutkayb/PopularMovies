package nd.centertableinc.popularmovies1.Activity;

/**
 * Created by Rutkay on 25.03.2018.
 */

public interface StateFactory{
    State getState(int state) throws UnknownStateError;
}
