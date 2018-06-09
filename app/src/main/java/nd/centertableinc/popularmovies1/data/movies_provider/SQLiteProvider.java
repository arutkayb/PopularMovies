package nd.centertableinc.popularmovies1.data.movies_provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nd.centertableinc.popularmovies1.data.recycler_view_items.MovieItem;


public class SQLiteProvider extends ContentProvider {
    private SQLiteDatabase sqLiteMoviesDb;

    public static final String AUTHORITY = "nd.centertableinc.popularmovies1.data.movies_provider";
    public static final String FAVORITES_DATA_PATH = "favorites";

    /*
    Currently we're going to supply 1 URI:
        1- Batch favorite movies: content://nd.centertableinc.popularmovies1.data.movies_provider/favorites
     */
    private static final int ALL_FAVORITES = 1;

    // URI Matcher preparations
    private static final UriMatcher URI_MATCHER;
    static {
        URI_MATCHER = new UriMatcher(UriMatcher.NO_MATCH);
        URI_MATCHER.addURI(AUTHORITY, FAVORITES_DATA_PATH ,ALL_FAVORITES);
    }

    @Override
    public boolean onCreate() {
        this.sqLiteMoviesDb = (new SQLiteMoviesDb(getContext())).getWritableDatabase();

        return (sqLiteMoviesDb != null);
    }

    @Override
    public String getType(Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case ALL_FAVORITES:
                return SQLiteMoviesFavorite.CONTENT_TYPE;
            default:
                return null;
        }
    }

    // The insert() method adds a new row to the appropriate table, using the values
    // in the ContentValues argument. If a column name is not in the ContentValues argument,
    // you may want to provide a default value for it either in your provider code or in
    // your database schema.
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        switch (URI_MATCHER.match(uri)) {
            case ALL_FAVORITES:
                long rowId = sqLiteMoviesDb.insertWithOnConflict(SQLiteMoviesFavorite.TABLE_NAME_MOVIES_FAVORITE, null, values, SQLiteDatabase.CONFLICT_REPLACE);

                if (rowId <= 0) {
                    Log.e(getClass().getName(),"Failed to add a record into " + uri);
                }

                break;

            default:
                break;
        }

        return null;
    }

    // The query() method must return a Cursor object, or if it fails,
    // throw an Exception. If you are using an SQLite database as your data storage,
    // you can simply return the Cursor returned by one of the query() methods of the
    // SQLiteDatabase class. If the query does not match any rows, you should return a
    // Cursor instance whose getCount() method returns 0. You should return null only
    // if an internal error occurred during the query process.
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder)
    {
        Cursor cursor = null;

        switch (URI_MATCHER.match(uri)) {
            case ALL_FAVORITES:
                cursor = sqLiteMoviesDb.query(SQLiteMoviesFavorite.TABLE_NAME_MOVIES_FAVORITE,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder);

                break;

            default:
                break;
        }

        return cursor;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int count = 0;

        switch (URI_MATCHER.match(uri)) {
            case ALL_FAVORITES:
                count = sqLiteMoviesDb.delete(SQLiteMoviesFavorite.TABLE_NAME_MOVIES_FAVORITE, selection, selectionArgs);

                break;
            default:

                break;
        }

        return count;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        int count = 0;

        switch (URI_MATCHER.match(uri)) {
            case ALL_FAVORITES:
                count = sqLiteMoviesDb.update(SQLiteMoviesFavorite.TABLE_NAME_MOVIES_FAVORITE, values, selection, selectionArgs);

                break;
            default:

                break;
        }

        return count;
    }
    
}
