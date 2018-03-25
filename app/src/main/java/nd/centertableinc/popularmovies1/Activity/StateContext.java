package nd.centertableinc.popularmovies1.Activity;

/**
 * Created by Rutkay on 25.03.2018.
 */

public interface StateContext <State>{
    State getCurrentState();
    void setState(int state) throws UnknownStateError;
}
