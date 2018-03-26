package nd.centertableinc.popularmovies1.Data.Utils;

import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;
import nd.centertableinc.popularmovies1.Data.TheMovieDb;

/**
 * Created by Rutkay on 14.03.2018.
 */

public class MovieItemUtil {
    private static final class TheMovieDbJsonFields {

        //TheMovieDb api returned json fields
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

    public static List<MovieItem> getMovieItemsFromTheMovieDbJson(JSONObject TheMovieDbJson)
    {
        List<MovieItem> movieItems = new ArrayList<>();

        if( TheMovieDbJson != null && TheMovieDbJson.has(TheMovieDbJsonFields.RESULTS))
        {
            JSONArray resultsArray = JsonUtil.getJsonArrayFromJsonObj(TheMovieDbJson, TheMovieDbJsonFields.RESULTS);
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

        if(movieJson.has(TheMovieDbJsonFields.VOTE_COUNT))
        {
            voteCount = JsonUtil.getIntFromJsonObj(movieJson, TheMovieDbJsonFields.VOTE_COUNT);
            movieItem.setVoteCount(voteCount);
        }

        if(movieJson.has(TheMovieDbJsonFields.ID))
        {
            id = JsonUtil.getIntFromJsonObj(movieJson, TheMovieDbJsonFields.ID);
            movieItem.setId(id);
        }

        if(movieJson.has(TheMovieDbJsonFields.VIDEO))
        {
            isVideo = JsonUtil.getBooleanFromJsonObj(movieJson, TheMovieDbJsonFields.VIDEO);
            movieItem.setVideo(isVideo);
        }

        if(movieJson.has(TheMovieDbJsonFields.VOTE_AVEGARE))
        {
            voteAverage = JsonUtil.getDoubleFromJsonObj(movieJson, TheMovieDbJsonFields.VOTE_AVEGARE);
            movieItem.setVoteAverage(voteAverage);
        }

        if(movieJson.has(TheMovieDbJsonFields.TITLE))
        {
            title = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbJsonFields.TITLE);
            movieItem.setTitle(title);
        }

        if(movieJson.has(TheMovieDbJsonFields.POPULARITY))
        {
            popularity = JsonUtil.getDoubleFromJsonObj(movieJson, TheMovieDbJsonFields.POPULARITY);
            movieItem.setPopularity(popularity);
        }

        if(movieJson.has(TheMovieDbJsonFields.POSTER_PATH))
        {
            posterPath = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbJsonFields.POSTER_PATH);
            movieItem.setPosterPath(posterPath);
        }

        if(movieJson.has(TheMovieDbJsonFields.ORIGINAL_LANGUAGE))
        {
            origLanguage = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbJsonFields.ORIGINAL_LANGUAGE);
            movieItem.setOrigLanguage(origLanguage);
        }

        if(movieJson.has(TheMovieDbJsonFields.ORIGINAL_TITLE))
        {
            origTitle = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbJsonFields.ORIGINAL_TITLE);
            movieItem.setOrigTitle(origTitle);
        }

        if(movieJson.has(TheMovieDbJsonFields.BACKDROP_PATH))
        {
            backdropPath = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbJsonFields.BACKDROP_PATH);
            movieItem.setBackdropPath(backdropPath);
        }

        if(movieJson.has(TheMovieDbJsonFields.ADULT))
        {
            isAdult = JsonUtil.getBooleanFromJsonObj(movieJson, TheMovieDbJsonFields.ADULT);
            movieItem.setAdult(isAdult);
        }

        if(movieJson.has(TheMovieDbJsonFields.OVERVIEW))
        {
            overview = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbJsonFields.OVERVIEW);
            movieItem.setOverview(overview);
        }

        if(movieJson.has(TheMovieDbJsonFields.RELEASE_DATE))
        {
            releaseDate = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbJsonFields.RELEASE_DATE);
            movieItem.setReleaseDate(releaseDate);
        }

        return movieItem;
    }

    public static String getLargeImageUrlFromImagePath(String imageName)
    {
        return getImageUrlFromImagePath(imageName, TheMovieDb.POSTER_W780);
    }
    public static String getSmallImageUrlFromImagePath(String imageName)
    {
        return getImageUrlFromImagePath(imageName, TheMovieDb.POSTER_W342);
    }

    private static String getImageUrlFromImagePath(String imageName, String size)
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http")
                .authority(TheMovieDb.POSTER_BASE_URL)
                .appendPath(TheMovieDb.POSTER_T)
                .appendPath(TheMovieDb.POSTER_P)
                .appendPath(size)
                .appendEncodedPath(imageName);

        return builder.build().toString();
    }

}
