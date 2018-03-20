package nd.centertableinc.popularmovies1.Data.Utils;

import android.graphics.Movie;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import nd.centertableinc.popularmovies1.Data.MovieDb;
import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;

/**
 * Created by Rutkay on 14.03.2018.
 */

public class MovieItemUtil {
    private static MovieItem selectedMovieItem;

    private static final class MovieDbJsonFields {

        //moviedb api returned json fields
        private static final String RESULTS = "results"; //json array
        private static final String PAGE = "page"; //int
        private static final String TOTAL_PAGES = "total_pages"; //int
        private static final String TOTAL_RESULTS = "total_results"; //int

        // json object fields of "results" array
        private static final String VOTE_COUNT = "vote_count"; //int
        private static final String ID = "id"; //int
        private static final String VIDEO = "video"; //boolean
        private static final String VOTE_AVEGARE = "vote_average"; //double
        private static final String TITLE = "title"; //string
        private static final String POPULARITY = "popularity"; //double
        private static final String POSTER_PATH = "poster_path"; //string
        private static final String ORIGINAL_LANGUAGE = "original_language"; //string
        private static final String ORIGINAL_TITLE = "original_title"; //string
        private static final String BACKDROP_PATH = "backdrop_path"; //string
        private static final String ADULT = "adult"; //boolean
        private static final String OVERVIEW = "overview"; //string
        private static final String RELEASE_DATE = "release_date"; //string
    }

    public static List<MovieItem> getMovieItemsFromMovieDbJson(JSONObject movieDbJson)
    {
        List<MovieItem> movieItems = new ArrayList<>();

        if( movieDbJson != null && movieDbJson.has(MovieDbJsonFields.RESULTS))
        {
            JSONArray resultsArray = JsonUtil.getJsonArrayFromJsonObj(movieDbJson, MovieDbJsonFields.RESULTS);
            if(resultsArray != null)
            {
                for(int i = 0; i < resultsArray.length(); ++i)
                {
                    MovieItem item = getMovieItemFromMovieJson(JsonUtil.getJsonObjectFromJsonArray(resultsArray, i));
                    movieItems.add(item);
                }
            }
        }

        return movieItems;
    }

    private static MovieItem getMovieItemFromMovieJson(JSONObject movieJson)
    {
        int voteCount = 0;
        int id = 0;
        boolean isVideo = false;
        double voteAverage = 0;
        String title = "";
        double popularity = 0;
        String posterPath = "";
        String origLanguage = "";
        String origTitle = "";
        String backdropPath = "";
        boolean isAdult = false;
        String overview = "";
        String releaseDate = "";

        MovieItem movieItem = new MovieItem();

        if(movieJson.has(MovieDbJsonFields.VOTE_COUNT))
        {
            voteCount = JsonUtil.getIntFromJsonObj(movieJson, MovieDbJsonFields.VOTE_COUNT);
            movieItem.setVoteCount(voteCount);
        }

        if(movieJson.has(MovieDbJsonFields.ID))
        {
            id = JsonUtil.getIntFromJsonObj(movieJson, MovieDbJsonFields.ID);
            movieItem.setId(id);
        }

        if(movieJson.has(MovieDbJsonFields.VIDEO))
        {
            isVideo = JsonUtil.getBooleanFromJsonObj(movieJson, MovieDbJsonFields.VIDEO);
            movieItem.setVideo(isVideo);
        }

        if(movieJson.has(MovieDbJsonFields.VOTE_AVEGARE))
        {
            voteAverage = JsonUtil.getDoubleFromJsonObj(movieJson, MovieDbJsonFields.VOTE_AVEGARE);
            movieItem.setVoteAverage(voteAverage);
        }

        if(movieJson.has(MovieDbJsonFields.TITLE))
        {
            title = JsonUtil.getStringFromJsonObj(movieJson, MovieDbJsonFields.TITLE);
            movieItem.setTitle(title);
        }

        if(movieJson.has(MovieDbJsonFields.POPULARITY))
        {
            popularity = JsonUtil.getDoubleFromJsonObj(movieJson, MovieDbJsonFields.POPULARITY);
            movieItem.setPopularity(popularity);
        }

        if(movieJson.has(MovieDbJsonFields.POSTER_PATH))
        {
            posterPath = JsonUtil.getStringFromJsonObj(movieJson, MovieDbJsonFields.POSTER_PATH);
            movieItem.setPosterPath(posterPath);
        }

        if(movieJson.has(MovieDbJsonFields.ORIGINAL_LANGUAGE))
        {
            origLanguage = JsonUtil.getStringFromJsonObj(movieJson, MovieDbJsonFields.ORIGINAL_LANGUAGE);
            movieItem.setOrigLanguage(origLanguage);
        }

        if(movieJson.has(MovieDbJsonFields.ORIGINAL_TITLE))
        {
            origTitle = JsonUtil.getStringFromJsonObj(movieJson, MovieDbJsonFields.ORIGINAL_TITLE);
            movieItem.setOrigTitle(origTitle);
        }

        if(movieJson.has(MovieDbJsonFields.BACKDROP_PATH))
        {
            backdropPath = JsonUtil.getStringFromJsonObj(movieJson, MovieDbJsonFields.BACKDROP_PATH);
            movieItem.setBackdropPath(backdropPath);
        }

        if(movieJson.has(MovieDbJsonFields.ADULT))
        {
            isAdult = JsonUtil.getBooleanFromJsonObj(movieJson, MovieDbJsonFields.ADULT);
            movieItem.setAdult(isAdult);
        }

        if(movieJson.has(MovieDbJsonFields.OVERVIEW))
        {
            overview = JsonUtil.getStringFromJsonObj(movieJson, MovieDbJsonFields.OVERVIEW);
            movieItem.setOverview(overview);
        }

        if(movieJson.has(MovieDbJsonFields.RELEASE_DATE))
        {
            releaseDate = JsonUtil.getStringFromJsonObj(movieJson, MovieDbJsonFields.RELEASE_DATE);
            movieItem.setReleaseDate(releaseDate);
        }

        return movieItem;
    }

    public static String getLargeImageUrlFromImagePath(String imageName)
    {
        return getImageUrlFromImagePath(imageName, MovieDb.POSTER_W780);
    }
    public static String getSmallImageUrlFromImagePath(String imageName)
    {
        return getImageUrlFromImagePath(imageName, MovieDb.POSTER_W342);
    }

    private static String getImageUrlFromImagePath(String imageName, String size)
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(MovieDb.POSTER_BASE_URL)
                .appendPath(MovieDb.POSTER_T)
                .appendPath(MovieDb.POSTER_P)
                .appendPath(size)
                .appendEncodedPath(imageName);

        return builder.build().toString();
    }

    public static void setSelectedMovieItem(MovieItem movieItem)
    {
        selectedMovieItem = movieItem;
    }

    public static MovieItem getSelectedMovieItem()
    {
        return selectedMovieItem;
    }
}
