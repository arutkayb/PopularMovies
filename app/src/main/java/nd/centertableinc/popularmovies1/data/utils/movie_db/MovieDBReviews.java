package nd.centertableinc.popularmovies1.data.utils.movie_db;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import nd.centertableinc.popularmovies1.R;
import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.movie.MovieReview;
import nd.centertableinc.popularmovies1.data.utils.HttpUtil;
import nd.centertableinc.popularmovies1.data.utils.JsonUtil;
import nd.centertableinc.popularmovies1.data.utils.MovieUtil;

public class MovieDBReviews implements MovieDb {
    private Context context;
    private AsyncDataListener listener;

    public MovieDBReviews(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext(){
        return context;
    }

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
        JSONObject res = JsonUtil.createJsonObjFromJsonString((String)result);

        if(res != null) {
            List<MovieReview> tempMovieReviews = MovieUtil.getMovieReviewsFromTheMovieDbJson(res);

            AsyncDataListener listener = getListener();
            if(listener != null)
                listener.onDataLoad(tempMovieReviews);
        }
    }

    private String getMovieReviewsAPIUriAsString(String movieId){
        String apiKey = context.getResources().getString(R.string.api_key);

        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(TheMovieDb.MOVIE_API_URL)
                .appendPath(TheMovieDb.MOVIE_API_VERSION_3)
                .appendPath(TheMovieDb.TYPE_MOVIE)
                .appendPath(movieId)
                .appendPath(TheMovieDb.REVIEWS)
                .appendQueryParameter(TheMovieDb.TAG_API_KEY, apiKey);

        return builder.build().toString();
    }

    public final void requestForMovieReviews(AsyncDataListener asyncDataListener, String movieId)
    {
        setListener(asyncDataListener);
        String movieReviewsUri = getMovieReviewsAPIUriAsString(movieId);

        try {
            HttpUtil.getRequest(this, getContext(), movieReviewsUri);
        }catch (IOException ex) {
            Log.e("MovieDb", "requestForMovieReviews IO Exception: " + ex.toString());
        }
    }

}
