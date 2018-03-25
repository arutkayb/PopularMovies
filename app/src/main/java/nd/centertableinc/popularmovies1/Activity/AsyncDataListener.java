package nd.centertableinc.popularmovies1.Activity;

/**
 * Created by Rutkay on 19.03.2018.
 */

public interface AsyncDataListener<Result> {
    void onDataLoad(Result result);
}
