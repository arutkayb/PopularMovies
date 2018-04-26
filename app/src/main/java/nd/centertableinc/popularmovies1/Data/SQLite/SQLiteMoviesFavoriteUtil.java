package nd.centertableinc.popularmovies1.Data.SQLite;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;

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
        final String SAVE_MOVIE = "INSERT "
        sqLiteDatabase.execSQL(SAVE_MOVIE);
    }
}
