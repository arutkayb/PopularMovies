package nd.centertableinc.popularmovies1.data;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.channels.AsynchronousChannel;
import java.util.List;

import nd.centertableinc.popularmovies1.R;
import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.movie.MovieItem;
import nd.centertableinc.popularmovies1.data.utils.HttpUtil;
import nd.centertableinc.popularmovies1.data.utils.JsonUtil;
import nd.centertableinc.popularmovies1.data.utils.MovieUtil;

/**
 * Created by abiyik on 26.03.2018.
 */

//Template class
public abstract class MovieData implements AsyncDataListener{
    protected abstract Context getContext();
    protected abstract String getMoviesAPIUriAsString(int page);
    protected abstract void setListener(AsyncDataListener listener);
    protected abstract AsyncDataListener getListener();

    private String getMovieReviewsAPIUriAsString(String movieId, int page){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(TheMovieDb.MOVIE_API_URL)
                .appendPath(TheMovieDb.MOVIE_API_VERSION_3)
                .appendPath(TheMovieDb.TYPE_MOVIE)
                .appendPath(movieId)
                .appendPath(TheMovieDb.REVIEWS);

        return builder.build().toString();
    }

    public final void requestForMovieReviews(AsyncDataListener asyncDataListener, String movieId, int page)
    {
        setListener(asyncDataListener);
        String movieReviewsUri = getMovieReviewsAPIUriAsString(movieId, page);

        try {
            HttpUtil.getRequest(this, getContext(), movieReviewsUri);
        }catch (IOException ex) {
            Log.e("MovieDb", "requestForMovieReviews IO Exception: " + ex.toString());
        }
    }

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

            getListener().onDataLoad(tempMovieItems);
        }
    }

}
