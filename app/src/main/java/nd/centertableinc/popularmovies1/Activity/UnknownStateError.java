package nd.centertableinc.popularmovies1.Activity;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class UnknownStateError extends Exception{
    public UnknownStateError (String message)
    {
        super(message);
    }

    public String getMessage()
    {
        return super.getMessage();
    }
}
