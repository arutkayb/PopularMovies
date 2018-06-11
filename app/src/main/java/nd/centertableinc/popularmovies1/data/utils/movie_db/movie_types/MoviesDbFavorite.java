package nd.centertableinc.popularmovies1.data.utils.movie_db.movie_types;

import android.content.Context;

import java.util.List;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.movie.MovieItem;
import nd.centertableinc.popularmovies1.data.utils.provider_utils.MoviesFavoriteUtil;

/**
 * Created by abiyik on 26.03.2018.
 */

public class MoviesDbFavorite extends MovieData {
    private Context context;
    private AsyncDataListener listener;

    public MoviesDbFavorite(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext(){
        return context;
    }

    @Override
    public void requestForMovies(AsyncDataListener asyncDataListener, int page) {
        setListener(asyncDataListener);
        MoviesFavoriteUtil.requestForMovies(context, this);
    }

    @Override
    protected String getMoviesAPIUriAsString(int page){return null;}


    @Override
    public void setListener(AsyncDataListener listener){
        this.listener = listener;
    }

    @Override
    public AsyncDataListener getListener(){
        return listener;
    }

    @Override
    public void onDataLoad(Object result){
        getListener().onDataLoad((List<MovieItem>) result);
    }
}
