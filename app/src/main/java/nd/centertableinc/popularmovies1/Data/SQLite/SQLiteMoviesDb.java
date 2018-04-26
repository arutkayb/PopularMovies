package nd.centertableinc.popularmovies1.Data.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// Contract class
public class SQLiteMoviesDb extends SQLiteOpenHelper{
    private static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;

    public SQLiteMoviesDb(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String SQL_CREATE_MOVIES_FAVORITE_TABLE = "CREATE TABLE "+
                SQLiteMoviesFavoriteUtil.TABLE_NAME_MOVIES_FAVORITE + "(" +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_MOVIE_ID + " INTEGER," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_VOTE_COUNT + " INTEGER," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_VOTE_AVERAGE + " REAL," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_TITLE + " TEXT," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_POPULARITY + " REAL," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_POSTER_PATH + " TEXT," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_ORIGINAL_LANGUAGE + " TEXT," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_ORIGINAL_TITLE + " TEXT," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_BACKDROP_PATH + " TEXT," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_IS_ADULT + " INTEGER," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_OVERVIEW + " TEXT," +
                SQLiteMoviesFavoriteUtil.FAVORITES_COLUMN_RELEASE_DATE + " TEXT" +
                ");";

        sqLiteDatabase.execSQL(SQL_CREATE_MOVIES_FAVORITE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + SQLiteMoviesFavoriteUtil.TABLE_NAME_MOVIES_FAVORITE);
        onCreate(sqLiteDatabase);
    }
}
