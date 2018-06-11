package nd.centertableinc.popularmovies1.data.utils.movie_db.movie_types;

import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.movie.MovieItem;
import nd.centertableinc.popularmovies1.data.utils.HttpUtil;
import nd.centertableinc.popularmovies1.data.utils.JsonUtil;
import nd.centertableinc.popularmovies1.data.utils.MovieUtil;
import nd.centertableinc.popularmovies1.data.utils.movie_db.MovieDb;

/**
 * Created by abiyik on 26.03.2018.
 */

//Template class
public abstract class MovieData implements MovieDb {
    protected abstract String getMoviesAPIUriAsString(int page);

    public void requestForMovies(AsyncDataListener asyncDataListener, int page)
    {
        setListener(asyncDataListener);
        String popularMoviesUrl = getMoviesAPIUriAsString(page);

        try {
            HttpUtil.getRequest(this, getContext(), popularMoviesUrl);
        }catch (IOException ex) {
            Log.e("MovieDb", "requestForMovies IO Exception: " + ex.toString());
        }
    }

    @Override
    public void onDataLoad(Object result){
        JSONObject res = JsonUtil.createJsonObjFromJsonString((String)result);

        if(res != null) {
            List<MovieItem> tempMovieItems = MovieUtil.getMovieItemsFromTheMovieDbJson(res);

            AsyncDataListener listener = getListener();
            if(listener != null)
                listener.onDataLoad(tempMovieItems);
        }
    }

}
