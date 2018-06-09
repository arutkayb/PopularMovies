package nd.centertableinc.popularmovies1.data.movies_provider;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class SQLiteMoviesFavorite implements SQLiteMovies {
    public static final String TABLE_NAME_MOVIES_FAVORITE = "MOVIES_FAVORITE";

    public static final String FAVORITES_COLUMN_ID = "_ID"; // auto increment uid
    public static final String FAVORITES_COLUMN_MOVIE_ID = "MOVIE_ID"; // real movie id from remote movie db
    public static final String FAVORITES_COLUMN_VOTE_COUNT = "VOTE_COUNT"; // int
    public static final String FAVORITES_COLUMN_IS_VIDEO = "IS_VIDEO"; // boolean
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

    public static final String FAVORITES_PROJECTION[] = {
            FAVORITES_COLUMN_ID,
            FAVORITES_COLUMN_MOVIE_ID,
            FAVORITES_COLUMN_VOTE_COUNT,
            FAVORITES_COLUMN_IS_VIDEO,
            FAVORITES_COLUMN_VOTE_AVERAGE,
            FAVORITES_COLUMN_TITLE,
            FAVORITES_COLUMN_POPULARITY,
            FAVORITES_COLUMN_POSTER_PATH,
            FAVORITES_COLUMN_ORIGINAL_LANGUAGE,
            FAVORITES_COLUMN_ORIGINAL_TITLE,
            FAVORITES_COLUMN_BACKDROP_PATH,
            FAVORITES_COLUMN_IS_ADULT,
            FAVORITES_COLUMN_OVERVIEW,
            FAVORITES_COLUMN_RELEASE_DATE
    };

    public static final String CONTENT_NAME = "nd.centertableinc.popularmovies1";
    public static final String CONTENT_TYPE = CONTENT_NAME + "." + "dir";
    public static final String CONTENT_ITEM_TYPE = CONTENT_NAME + "." + "item";

    public static final Uri FAVORITE_MOVIE_PROVIDER_URI;

    static {
        Uri.Builder builder = new Uri.Builder();
        FAVORITE_MOVIE_PROVIDER_URI = builder
                .scheme("content")
                .authority(SQLiteProvider.AUTHORITY)
                .appendPath(SQLiteProvider.FAVORITES_DATA_PATH)
                .build();
    }

    private SQLiteDatabase sqLiteDatabase;

    void SQLiteMoviesFavorite(){

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIES_FAVORITE_TABLE = "CREATE TABLE "+
                TABLE_NAME_MOVIES_FAVORITE + "(" +
                FAVORITES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                FAVORITES_COLUMN_MOVIE_ID + " INTEGER," +
                FAVORITES_COLUMN_VOTE_COUNT + " INTEGER," +
                FAVORITES_COLUMN_IS_VIDEO + " INTEGER," +
                FAVORITES_COLUMN_VOTE_AVERAGE + " REAL," +
                FAVORITES_COLUMN_TITLE + " TEXT," +
                FAVORITES_COLUMN_POPULARITY + " REAL," +
                FAVORITES_COLUMN_POSTER_PATH + " TEXT," +
                FAVORITES_COLUMN_ORIGINAL_LANGUAGE + " TEXT," +
                FAVORITES_COLUMN_ORIGINAL_TITLE + " TEXT," +
                FAVORITES_COLUMN_BACKDROP_PATH + " TEXT," +
                FAVORITES_COLUMN_IS_ADULT + " INTEGER," +
                FAVORITES_COLUMN_OVERVIEW + " TEXT," +
                FAVORITES_COLUMN_RELEASE_DATE + " TEXT" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SQLiteMoviesFavorite.TABLE_NAME_MOVIES_FAVORITE);
        onCreate(sqLiteDatabase);
    }

}
