package nd.centertableinc.popularmovies1.data.utils.movie_db.movie_types;

import android.content.Context;
import android.net.Uri;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.utils.movie_db.TheMovieDb;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by abiyik on 26.03.2018.
 */

public class MovieDbPopular extends MovieData {
    private Context context;
    private AsyncDataListener listener;

    public MovieDbPopular(Context context){
        this.context = context;
    }

    @Override
    public Context getContext(){
        return context;
    }

    @Override
    protected String getMoviesAPIUriAsString(int page){
        String apiKey = context.getResources().getString(R.string.api_key);

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
    public void setListener(AsyncDataListener listener){
        this.listener = listener;
    }

    @Override
    public AsyncDataListener getListener(){
        return listener;
    }
}
