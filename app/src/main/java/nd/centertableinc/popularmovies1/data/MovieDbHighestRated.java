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

public class MovieDbHighestRated implements MovieData {
    private String apiKey;
    private Context context;
    private HttpUtil httpUtil;

    public MovieDbHighestRated(Context context){
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
                .appendPath(TheMovieDb.TOP_RATED)
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
