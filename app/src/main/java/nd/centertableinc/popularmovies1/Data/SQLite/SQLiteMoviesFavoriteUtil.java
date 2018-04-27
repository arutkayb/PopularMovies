package nd.centertableinc.popularmovies1.Data.SQLite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.HashMap;
import java.util.Map;

import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;
import nd.centertableinc.popularmovies1.Data.Utils.StringUtil;

public class SQLiteMoviesFavoriteUtil {
    public static final String TABLE_NAME_MOVIES_FAVORITE = "MOVIES_FAVORITE";

    public static final String FAVORITES_COLUMN_ID = "_ID"; // auto increment uid
    public static final String FAVORITES_COLUMN_MOVIE_ID = "MOVIE_ID"; // real movie id from remote movie db
    public static final String FAVORITES_COLUMN_VOTE_COUNT = "VOTE_COUNT"; // int
    public static final String FAVORITES_COLUMN_VOTE_AVERAGE = "VOTE_AVERAGE"; // double
    public static final String FAVORITES_COLUMN_TITLE = "TITLE"; // string
    public static final String FAVORITES_COLUMN_POPULARITY = "POPULARITY"; // double
    public static final String FAVORITES_COLUMN_POSTER_PATH = "POSTER_PATH"; // string
    public static final String FAVORITES_COLUMN_ORIGINAL_LANGUAGE = "ORIGINAL_LANGUAGE"; // string
    public static final String FAVORITES_COLUMN_ORIGINAL_TITLE = "ORIGINAL_TITLE"; // string
    public static final String FAVORITES_COLUMN_BACKDROP_PATH = "BACKDROP_PATH"; // string
    public static final String FAVORITES_COLUMN_IS_ADULT = "IS_ADULT"; // boolean
    public static final String FAVORITES_COLUMN_OVERVIEW = "OVERVIEW"; // string
    public static final String FAVORITES_COLUMN_RELEASE_DATE = "RELEASE_DATE"; // string

    private SQLiteDatabase sqLiteDatabase;

    public SQLiteMoviesFavoriteUtil(SQLiteDatabase sqLiteDatabase)
    {
        this.sqLiteDatabase = sqLiteDatabase;
    }

    public void saveMovie(MovieItem movieItem)
    {
        String query;
        Map<String, String> record = new HashMap<>();

        record.put(FAVORITES_COLUMN_MOVIE_ID, String.valueOf(movieItem.getId()));
        record.put(FAVORITES_COLUMN_VOTE_COUNT, String.valueOf(movieItem.getVoteCount()));
        record.put(FAVORITES_COLUMN_VOTE_AVERAGE, String.valueOf(movieItem.getVoteAverage()));
        record.put(FAVORITES_COLUMN_TITLE, movieItem.getTitle());
        record.put(FAVORITES_COLUMN_POPULARITY, String.valueOf(movieItem.getPopularity()));
        record.put(FAVORITES_COLUMN_POSTER_PATH, movieItem.getPosterPath());
        record.put(FAVORITES_COLUMN_ORIGINAL_LANGUAGE, movieItem.getOrigLanguage());
        record.put(FAVORITES_COLUMN_ORIGINAL_TITLE, movieItem.getOrigTitle());
        record.put(FAVORITES_COLUMN_BACKDROP_PATH, movieItem.getBackdropPath());
        record.put(FAVORITES_COLUMN_IS_ADULT, String.valueOf(movieItem.isAdult()));
        record.put(FAVORITES_COLUMN_OVERVIEW, movieItem.getOverview());
        record.put(FAVORITES_COLUMN_RELEASE_DATE, movieItem.getReleaseDate());

        query = SQLiteUtil.getInsertQuery(TABLE_NAME_MOVIES_FAVORITE, record);
        sqLiteDatabase.execSQL(query);
    }

    public MovieItem getMovie(String movieId)
    {
        String query;
        Map<String, String> record = new HashMap<>();

        record.put(FAVORITES_COLUMN_MOVIE_ID, movieId);

        query = SQLiteUtil.getSelectQuery(TABLE_NAME_MOVIES_FAVORITE, null, record);

        //TODO: fill here
        return null;
    }

    public void deleteMovie(String movieId)
    {
        Map<String, String> record = new HashMap<>();
        record.put(FAVORITES_COLUMN_MOVIE_ID, movieId);

        String query = SQLiteUtil.getDeleteQuery(TABLE_NAME_MOVIES_FAVORITE, record);

        sqLiteDatabase.execSQL(query);
    }
}
