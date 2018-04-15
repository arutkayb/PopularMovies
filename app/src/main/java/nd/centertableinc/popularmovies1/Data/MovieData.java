package nd.centertableinc.popularmovies1.Data;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;

/**
 * Created by abiyik on 26.03.2018.
 */

public interface MovieData {
    void requestForMovies(int page, AsyncDataListener asyncDataListener);
}
