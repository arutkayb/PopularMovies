package nd.centertableinc.popularmovies1.data;

import android.content.Context;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.utils.provider_utils.MoviesFavoriteUtil;

/**
 * Created by abiyik on 26.03.2018.
 */

public class MoviesDbFavorite implements MovieData {
    private Context context;

    public MoviesDbFavorite(Context context) {
        this.context = context;
    }

    @Override
    public void requestForMovies(int page, AsyncDataListener asyncDataListener) {
        MoviesFavoriteUtil.requestForMovies(context, asyncDataListener);
    }

}
