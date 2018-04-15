package nd.centertableinc.popularmovies1.Data;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.Data.Utils.HttpUtil;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by abiyik on 26.03.2018.
 */

public class MovieDbPopular implements MovieData{
    private String apiKey;
    private Context context;
    private HttpUtil httpUtil;

    public MovieDbPopular(Context context){
        this.context = context;
        this.apiKey = context.getResources().getString(R.string.api_key);

        httpUtil = new HttpUtil(context);
    }

    @Override
    public void requestForMovies(int page, AsyncDataListener asyncDataListener)
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(TheMovieDb.MOVIE_API_URL)
                .appendPath(TheMovieDb.MOVIE_API_VERSION_3)
                .appendPath(TheMovieDb.TYPE_MOVIE)
                .appendPath(TheMovieDb.POPULAR)
                .appendQueryParameter(TheMovieDb.PAGE, String.valueOf(page))
                .appendQueryParameter(TheMovieDb.TAG_API_KEY, apiKey);

        String popularMoviesUrl = builder.build().toString();

        try {
            httpUtil.getRequest(popularMoviesUrl, asyncDataListener);
        }catch (IOException ex)
        {
            Log.e("MovieDb", "requestForTheMostPopularMovies IO Exception: " + ex.toString());
        }
    }
}
