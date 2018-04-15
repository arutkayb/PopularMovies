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

public class MovieDbPopular extends TheMovieDb{
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
                .authority(MOVIE_API_URL)
                .appendPath(MOVIE_API_VERSION_3)
                .appendPath(TYPE_MOVIE)
                .appendPath(POPULAR)
                .appendQueryParameter(PAGE, String.valueOf(page))
                .appendQueryParameter(TAG_API_KEY, apiKey);

        String popularMoviesUrl = builder.build().toString();

        try {
            httpUtil.getRequest(popularMoviesUrl, asyncDataListener);
        }catch (IOException ex)
        {
            Log.e("MovieDb", "requestForTheMostPopularMovies IO Exception: " + ex.toString());
        }
    }
}
