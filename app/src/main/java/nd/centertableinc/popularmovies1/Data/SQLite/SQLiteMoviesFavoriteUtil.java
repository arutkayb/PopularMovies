package nd.centertableinc.popularmovies1.Data.SQLite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
        ContentValues record = new ContentValues();

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

        sqLiteDatabase.insert(TABLE_NAME_MOVIES_FAVORITE, null, record);
        sqLiteDatabase.close();
    }

    public List<MovieItem> getAllMovies()
    {
        MovieItem movieItem;
        List<MovieItem> movieItems = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME_MOVIES_FAVORITE;

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                if((movieItem = getMovieItemViaCursor(cursor)) != null)
                    movieItems.add(movieItem);

            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();
        return movieItems;
    }

    public boolean isFav(int movieId)
    {
        List<MovieItem> movieItems = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_NAME_MOVIES_FAVORITE + " WHERE "
                + FAVORITES_COLUMN_MOVIE_ID + " = " + String.valueOf(movieId);

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        sqLiteDatabase.close();
        return cursor.getCount() > 0;
    }

    public boolean deleteMovie(String movieId)
    {
        String whereClause = FAVORITES_COLUMN_MOVIE_ID + " = " + movieId;
        boolean res = sqLiteDatabase.delete(TABLE_NAME_MOVIES_FAVORITE, whereClause, null) > 0;

        sqLiteDatabase.close();

        return res;
    }

    public MovieItem getMovieItemViaCursor(Cursor cursor)
    {
        MovieItem movieItem = new MovieItem();

        try {
            movieItem.setVoteCount(cursor.getInt(cursor.getColumnIndex(FAVORITES_COLUMN_VOTE_COUNT)));
            movieItem.setVoteAverage(cursor.getDouble(cursor.getColumnIndex(FAVORITES_COLUMN_VOTE_AVERAGE)));
            movieItem.setTitle(cursor.getString(cursor.getColumnIndex(FAVORITES_COLUMN_TITLE)));
            movieItem.setPopularity(cursor.getDouble(cursor.getColumnIndex(FAVORITES_COLUMN_POPULARITY)));
            movieItem.setPosterPath(cursor.getString(cursor.getColumnIndex(FAVORITES_COLUMN_POSTER_PATH)));
            movieItem.setOrigLanguage(cursor.getString(cursor.getColumnIndex(FAVORITES_COLUMN_ORIGINAL_LANGUAGE)));
            movieItem.setOrigTitle(cursor.getString(cursor.getColumnIndex(FAVORITES_COLUMN_ORIGINAL_TITLE)));
            movieItem.setBackdropPath(cursor.getString(cursor.getColumnIndex(FAVORITES_COLUMN_BACKDROP_PATH)));
            movieItem.setAdult(cursor.getInt(cursor.getColumnIndex(FAVORITES_COLUMN_IS_ADULT)) > 0);
            movieItem.setOverview(cursor.getString(cursor.getColumnIndex(FAVORITES_COLUMN_OVERVIEW)));
            movieItem.setReleaseDate(cursor.getString(cursor.getColumnIndex(FAVORITES_COLUMN_RELEASE_DATE)));
        }
        catch (SQLException ex)
        {
            movieItem = null;
            Log.e(this.getClass().getName(), ex.toString());
        }

        return movieItem;
    }
}
