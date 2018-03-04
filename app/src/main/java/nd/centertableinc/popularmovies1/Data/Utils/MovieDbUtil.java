package nd.centertableinc.popularmovies1.Data.Utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Rutkay on 04.03.2018.
 */

public class MovieDbUtil {
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

    private final static String API_URL = "http://api.themoviedb.org/3";
    public final static String TYPE_MOVIE = "/movie";
    public final static String TAG_POPULAR = "/popular?";
    private final static String TAG_API_KEY = "/api_key=";

    private String mApiKey;
    public MovieDbUtil(String apiKey){
        mApiKey = apiKey;
    }



}
