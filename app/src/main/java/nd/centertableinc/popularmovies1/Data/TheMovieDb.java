package nd.centertableinc.popularmovies1.Data;

import nd.centertableinc.popularmovies1.Activity.AsyncDataListener;

/**
 * Created by abiyik on 26.03.2018.
 */

public class TheMovieDb{
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
    public final static String TOP_RATED = "top_rated";
    public final static String POPULAR = "popular";

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

    private TheMovieDb()
    {

    }
}
