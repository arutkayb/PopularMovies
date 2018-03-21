package nd.centertableinc.popularmovies1.Data;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import nd.centertableinc.popularmovies1.Data.Utils.HttpUtil;
import nd.centertableinc.popularmovies1.Data.Utils.JsonUtil;
import nd.centertableinc.popularmovies1.Interfaces.AsyncDataListener;

/**
 * Created by Rutkay on 04.03.2018.
 */

public class MovieDb {
    /*
    * API themoviedb.org
    * It’s constructed using 3 parts:
    * The base URL will look like: http://image.tmdb.org/t/p/.
    * Then you will need a ‘size’, which will be one of the following:
    *   "w92", "w154", "w185", "w342", "w500", "w780", or "original". For most phones we recommend using “w185”.
    * And finally the poster path returned by the query, in this case “/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg”
    * Combining these three parts gives us a final url of http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
    *
    * example request:
    *   http://api.themoviedb.org/3/movie/popular?api_key=599c34d73931abe6918cfd12792fd160
    *
    * example Picasso usage
    *   Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
    */

    public final static String MOVIE_API_URL = "api.themoviedb.org";
    public final static String MOVIE_API_VERSION_3 = "3";
    public final static String TYPE_MOVIE = "movie";
    public final static String TAG_API_KEY = "api_key";
    public final static String SORT_BY = "sort_by";
    public final static String POPULARITY_DESC= "popularity.desc";
    public final static String DISCOVER = "discover";
    public final static String VOTE_AVERAGE_DESC = "vote_average.desc";
    public final static String PAGE = "page";

    public final static String POSTER_BASE_URL = "image.tmdb.org";
    public final static String POSTER_T = "t";
    public final static String POSTER_P = "p";

    /* Valid poster sizes */
    public final static String POSTER_W185 = "w185";
    public final static String POSTER_W342 = "w342";
    public final static String POSTER_W500 = "w500";
    public final static String POSTER_W780 = "w780";

    // size can be "w92", "w154", "w185", "w342", "w500", "w780"
    // overall poster link is like: http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg

    private String apiKey;
    private AsyncDataListener asyncDataListener;
    private Context context;
    private HttpUtil httpUtil;

    private int currentPage;

    public enum ORDER_TYPE{
        MOST_POPULAR,
        HIGHEST_RATED
    }

    public MovieDb(Context context, AsyncDataListener asyncDataListener, String apiKey){
        currentPage = 1;
        this.apiKey = apiKey;
        this.asyncDataListener = asyncDataListener;
        this.context = context;

        httpUtil = new HttpUtil(context, asyncDataListener);
    }

    public void requestForTheMostPopularMovies()
    {
        requestForTheMostPopularMovies(1);
    }

    public void requestForTheMostPopularMovies(int page)
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(MOVIE_API_URL)
                .appendPath(MOVIE_API_VERSION_3)
                .appendPath(DISCOVER)
                .appendPath(TYPE_MOVIE)
                .appendQueryParameter(PAGE, String.valueOf(page))
                .appendQueryParameter(TAG_API_KEY, apiKey)
                .appendQueryParameter(SORT_BY, POPULARITY_DESC);

        String popularMoviesUrl = builder.build().toString();

        try {
            httpUtil.getRequest(popularMoviesUrl);
        }catch (IOException ex)
        {
            Log.e("MovieDb", "requestForTheMostPopularMovies IO Exception: " + ex.toString());
        }

        currentPage = page;
    }

    public void requestForTheHighestRatedMovies()
    {
        requestForTheHighestRatedMovies(1);
    }

    public void requestForTheHighestRatedMovies(int page)
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(MOVIE_API_URL)
                .appendPath(MOVIE_API_VERSION_3)
                .appendPath(DISCOVER)
                .appendPath(TYPE_MOVIE)
                .appendQueryParameter(PAGE, String.valueOf(page))
                .appendQueryParameter(TAG_API_KEY, apiKey)
                .appendQueryParameter(SORT_BY, VOTE_AVERAGE_DESC);

        String popularMoviesUrl = builder.build().toString();

        try {
            httpUtil.getRequest(popularMoviesUrl);
        }catch (IOException ex)
        {
            Log.e("MovieDb", "requestForTheMostPopularMovies IO Exception: " + ex.toString());
        }

        currentPage = page;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }

}
