package nd.centertableinc.popularmovies1.data.utils.provider_utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Movie;

import java.util.ArrayList;
import java.util.List;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;
import nd.centertableinc.popularmovies1.data.movies_provider.SQLiteMoviesFavorite;
import nd.centertableinc.popularmovies1.data.movies_provider.SQLiteProvider;
import nd.centertableinc.popularmovies1.data.recycler_view_items.MovieItem;
import nd.centertableinc.popularmovies1.data.utils.MovieItemUtil;

import static nd.centertableinc.popularmovies1.data.movies_provider.SQLiteMoviesFavorite.*;

public class MoviesFavoriteUtil {
    private MoviesFavoriteUtil(){}

    public static void requestForMovies(Context context, AsyncDataListener<List<MovieItem>> asyncDataListener){
        Cursor cursor = context.getContentResolver().query(FAVORITE_MOVIE_PROVIDER_URI,
                SQLiteMoviesFavorite.FAVORITES_PROJECTION,
                null,
                null,
                null);

        List<MovieItem> movieItems = MovieItemUtil.getMovieItemsFromTheMovieCursor(cursor);

        asyncDataListener.onDataLoad(movieItems);
    }

    public static boolean isMovieAlreadyFavorite(Context context, MovieItem item){
        Cursor cursor = context.getContentResolver().query(FAVORITE_MOVIE_PROVIDER_URI,
                SQLiteMoviesFavorite.FAVORITES_PROJECTION,
                SQLiteMoviesFavorite.FAVORITES_COLUMN_MOVIE_ID + "=?",
                new String[] {String.valueOf(item.getId())},
                null);

        return (cursor.getCount() > 0);
    }

    public static void addMovieToFavorites(Context context, MovieItem item){
        ContentValues record = new ContentValues();

        record.put(FAVORITES_COLUMN_MOVIE_ID, String.valueOf(item.getId()));
        record.put(FAVORITES_COLUMN_VOTE_COUNT, String.valueOf(item.getVoteCount()));
        record.put(FAVORITES_COLUMN_IS_VIDEO, String.valueOf(item.isVideo()?1:0));
        record.put(FAVORITES_COLUMN_VOTE_AVERAGE, String.valueOf(item.getVoteAverage()));
        record.put(FAVORITES_COLUMN_TITLE, item.getTitle());
        record.put(FAVORITES_COLUMN_POPULARITY, String.valueOf(item.getPopularity()));
        record.put(FAVORITES_COLUMN_POSTER_PATH, item.getPosterPath());
        record.put(FAVORITES_COLUMN_ORIGINAL_LANGUAGE, item.getOrigLanguage());
        record.put(FAVORITES_COLUMN_ORIGINAL_TITLE, item.getOrigTitle());
        record.put(FAVORITES_COLUMN_BACKDROP_PATH, item.getBackdropPath());
        record.put(FAVORITES_COLUMN_IS_ADULT, String.valueOf(item.isAdult()?1:0));
        record.put(FAVORITES_COLUMN_OVERVIEW, item.getOverview());
        record.put(FAVORITES_COLUMN_RELEASE_DATE, item.getReleaseDate());

        context.getContentResolver().insert(FAVORITE_MOVIE_PROVIDER_URI, record);
    }

    public static void removeMoviteFromFavorites(Context context, MovieItem item){
        context.getContentResolver().delete(FAVORITE_MOVIE_PROVIDER_URI,
                FAVORITES_COLUMN_MOVIE_ID + "=?",
                new String[]{String.valueOf(item.getId())});
    }

}
