package nd.centertableinc.popularmovies1.data;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.utils.HttpUtil;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by abiyik on 26.03.2018.
 */

public class MovieDbPopular extends MovieData{
    private String apiKey;
    private Context context;
    private AsyncDataListener listener;

    public MovieDbPopular(Context context){
        this.context = context;
        this.apiKey = context.getResources().getString(R.string.api_key);
    }

    @Override
    protected Context getContext(){
        return context;
    }

    @Override
    protected String getMoviesAPIUriAsString(int page){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(TheMovieDb.MOVIE_API_URL)
                .appendPath(TheMovieDb.MOVIE_API_VERSION_3)
                .appendPath(TheMovieDb.TYPE_MOVIE)
                .appendPath(TheMovieDb.POPULAR)
                .appendQueryParameter(TheMovieDb.PAGE, String.valueOf(page))
                .appendQueryParameter(TheMovieDb.TAG_API_KEY, apiKey);

        return builder.build().toString();
    }

    @Override
    protected void setListener(AsyncDataListener listener){
        this.listener = listener;
    }

    @Override
    protected AsyncDataListener getListener(){
        return listener;
    }
}
