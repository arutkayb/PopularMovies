package nd.centertableinc.popularmovies1.data;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;

/**
 * Created by abiyik on 26.03.2018.
 */

public interface MovieData {
    void requestForMovies(int page, AsyncDataListener asyncDataListener);
}
