package nd.centertableinc.popularmovies1.data.utils;

import android.database.Cursor;
import android.net.Uri;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import nd.centertableinc.popularmovies1.data.movie.MovieReview;
import nd.centertableinc.popularmovies1.data.movies_provider.SQLiteMoviesFavorite;
import nd.centertableinc.popularmovies1.data.movie.MovieItem;
import nd.centertableinc.popularmovies1.data.TheMovieDb;

/**
 * Created by Rutkay on 14.03.2018.
 */

public class MovieUtil {
    private static final class TheMovieDbCommonJsonFields {

        //TheMovieDb api returned json fields
        private static final String RESULTS = "results"; //json array
        private static final String PAGE = "page"; //int
        private static final String TOTAL_PAGES = "total_pages"; //int
        private static final String TOTAL_RESULTS = "total_results"; //int
    }

    private static final class TheMovieDbMovieJsonFields {
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

    private static final class TheMovieDbMovieReviewJsonFields {
        // json object fields of "results" array
        private static final String ID = "id"; //string
        private static final String AUTHOR = "author"; //string
        private static final String CONTENT = "content"; //string
        private static final String URL = "url"; //string
    }

    public static List<MovieItem> getMovieItemsFromTheMovieCursor(Cursor cursor)
    {
        List<MovieItem> movieItems = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            MovieItem movie = null;

            do{
                movie = getMovieItemFromMovieCursor(cursor);
                movieItems.add(movie);
            }while(cursor.moveToNext());
        }

        return movieItems;
    }

    private static MovieItem getMovieItemFromMovieCursor(Cursor cursor)
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

        voteCount = cursor.getInt(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_VOTE_COUNT));
        movieItem.setVoteCount(voteCount);

        id = cursor.getInt(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_MOVIE_ID));
        movieItem.setId(id);


        isVideo = cursor.getInt(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_IS_VIDEO))==1;
        movieItem.setVideo(isVideo);


        voteAverage = cursor.getDouble(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_VOTE_AVERAGE));
        movieItem.setVoteAverage(voteAverage);


        title = cursor.getString(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_TITLE));
        movieItem.setTitle(title);

        popularity = cursor.getDouble(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_POPULARITY));
        movieItem.setPopularity(popularity);

        posterPath = cursor.getString(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_POSTER_PATH));
        movieItem.setPosterPath(posterPath);


        origLanguage = cursor.getString(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_ORIGINAL_LANGUAGE));
        movieItem.setOrigLanguage(origLanguage);


        origTitle = cursor.getString(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_ORIGINAL_TITLE));
        movieItem.setOrigTitle(origTitle);

        backdropPath = cursor.getString(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_BACKDROP_PATH));
        movieItem.setBackdropPath(backdropPath);

        isAdult = cursor.getInt(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_IS_ADULT))==1;
        movieItem.setAdult(isAdult);

        overview = cursor.getString(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_OVERVIEW));
        movieItem.setOverview(overview);

        releaseDate = cursor.getString(cursor.getColumnIndex(SQLiteMoviesFavorite.FAVORITES_COLUMN_RELEASE_DATE));
        movieItem.setReleaseDate(releaseDate);

        return movieItem;
    }


    /*
    Themoviedb API result schema:
    {
      "page": 1,
      "results": [
        {
          "poster_path": null,
          "adult": false,
          "overview": "Go behind the scenes during One Directions sell out \"Take Me Home\" tour and experience life on the road.",
          "release_date": "2013-08-30",
          "genre_ids": [
            99,
            10402
          ],
          "id": 164558,
          "original_title": "One Direction: This Is Us",
          "original_language": "en",
          "title": "One Direction: This Is Us",
          "backdrop_path": null,
          "popularity": 1.166982,
          "vote_count": 55,
          "video": false,
          "vote_average": 8.45
        },
        {
          "poster_path": null,
          "adult": false,
          "overview": "",
          "release_date": "1954-06-22",
          "genre_ids": [
            80,
            18
          ],
          "id": 654,
          "original_title": "On the Waterfront",
          "original_language": "en",
          "title": "On the Waterfront",
          "backdrop_path": null,
          "popularity": 1.07031,
          "vote_count": 51,
          "video": false,
          "vote_average": 8.19
        }
      ],
      "total_results": 2,
      "total_pages": 1
    }
    */
    public static List<MovieItem> getMovieItemsFromTheMovieDbJson(JSONObject TheMovieDbJson)
    {
        List<MovieItem> movieItems = new ArrayList<>();

        if( TheMovieDbJson != null && TheMovieDbJson.has(TheMovieDbCommonJsonFields.RESULTS))
        {
            JSONArray resultsArray = JsonUtil.getJsonArrayFromJsonObj(TheMovieDbJson, TheMovieDbCommonJsonFields.RESULTS);
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

        if(movieJson.has(TheMovieDbMovieJsonFields.VOTE_COUNT))
        {
            voteCount = JsonUtil.getIntFromJsonObj(movieJson, TheMovieDbMovieJsonFields.VOTE_COUNT);
            movieItem.setVoteCount(voteCount);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.ID))
        {
            id = JsonUtil.getIntFromJsonObj(movieJson, TheMovieDbMovieJsonFields.ID);
            movieItem.setId(id);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.VIDEO))
        {
            isVideo = JsonUtil.getBooleanFromJsonObj(movieJson, TheMovieDbMovieJsonFields.VIDEO);
            movieItem.setVideo(isVideo);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.VOTE_AVEGARE))
        {
            voteAverage = JsonUtil.getDoubleFromJsonObj(movieJson, TheMovieDbMovieJsonFields.VOTE_AVEGARE);
            movieItem.setVoteAverage(voteAverage);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.TITLE))
        {
            title = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieJsonFields.TITLE);
            movieItem.setTitle(title);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.POPULARITY))
        {
            popularity = JsonUtil.getDoubleFromJsonObj(movieJson, TheMovieDbMovieJsonFields.POPULARITY);
            movieItem.setPopularity(popularity);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.POSTER_PATH))
        {
            posterPath = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieJsonFields.POSTER_PATH);
            movieItem.setPosterPath(posterPath);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.ORIGINAL_LANGUAGE))
        {
            origLanguage = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieJsonFields.ORIGINAL_LANGUAGE);
            movieItem.setOrigLanguage(origLanguage);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.ORIGINAL_TITLE))
        {
            origTitle = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieJsonFields.ORIGINAL_TITLE);
            movieItem.setOrigTitle(origTitle);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.BACKDROP_PATH))
        {
            backdropPath = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieJsonFields.BACKDROP_PATH);
            movieItem.setBackdropPath(backdropPath);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.ADULT))
        {
            isAdult = JsonUtil.getBooleanFromJsonObj(movieJson, TheMovieDbMovieJsonFields.ADULT);
            movieItem.setAdult(isAdult);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.OVERVIEW))
        {
            overview = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieJsonFields.OVERVIEW);
            movieItem.setOverview(overview);
        }

        if(movieJson.has(TheMovieDbMovieJsonFields.RELEASE_DATE))
        {
            releaseDate = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieJsonFields.RELEASE_DATE);
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

    /*
    TheMovieDb API result schema:
        {
          "id": 297761,
          "page": 1,
          "results": [
            {
              "id": "57a814dc9251415cfb00309a",
              "author": "Frank Ochieng",
              "content": "Summertime 2016 has not been very kind to DC Comics-based personalities looking to shine consistently like their big screen Marvel Comics counterparts. Following the super-sized dud that was _Batman v. Superman: Dawn of Justice_ released a few months ago must really put some major pressure on Warner Bros. to gamble on ensuring that the presence of **Suicide Squad** does not meet the same kind of indifferent reception. Well, it turns out that although the anticipation was high for writer-director David Ayer's supervillain saga involving high-powered imprisoned rogues recruited as U.S. governmental operatives out to stop other skillful baddies (as it was for Zack Ryder's aforementioned \"Dawn of Justice\") the concoction of **Suicide Squad** feels like a colorful mishmash of collective misfits laboriously taking up space in a disjointed eye candy-coated spectacle that never manages to match its intended sizzle.\r\n\r\nOne would think that the premise for **Suicide Squad** would tap into the intriguing naughtiness with more robust gumption given the collection of super-powered oddballs asked to be immediate anti-heroes in this toothless jamboree of renegade rejects. Strangely, the grim and brooding presentation of **Suicide Squad** is more of an erratic downer than a hyperactive high-wire act as intended at the creative hands of Ayer. There is no reason why this lively group of adventurous agitators should appear so flat and inconsequential in a boisterous blockbuster that sporadically limps.\r\n\r\nGiven the twisted members that comprise this elite team of terrorizing tools it is very disappointing to see how **Suicide Squad** struggles with its so-called subversive themes. Sadly, this splattered mess never firmly grasps its bid for distinctive irreverence or off-balance exploitation. Instead, **Squad** feels strained in its execution and we are never really invested in entirely watching these treasured troublemakers find redemption because the story is soggy and uninspired. Furthermore, not all of the **Squad** participants are fleshed out satisfyingly for us to get behind with thirsty cynicism. The headlining leads in Will Smith's Floyd Lawton/Deadshot, Oscar-winner Jared Leto's green-haired Joker and Australian beauty Margot Robbie's Harleen Quinzel/Harley Quinn get the meaty standout parts while the lesser known supporting cast get stuck with chewing on the thankless remaining bone while seemingly acting as background furniture to the bigger names.\r\n\r\nNaturally, desperation has set in for the U.S. government as they need to safeguard national security against advanced sinister forces that threaten the fiber of American self-interests everywhere. What better way to hire gifted protection than to consider employing the world's most incarcerated corruptible, cutthroat cretins to perform the dirty work in unforgivable mission ops that require death-defying determination. Enter U.S. Intelligence agent Amanda Waller (Oscar nominee Viola Davis). Waller's duties are to assemble the ragtag team known as the Suicide Squad--ominous (yet talented) jailbirds tapped to step in and assume superhero status (especially when the real superheroes are tied up in other crime-stopping affairs) while helping out for the greater good of our vulnerable society. In exchange for the Suicide Squad's sacrifice in turning from hell-bent heels to reluctant heralded heroes they are promised commuted prison sentences should they effectively defend and destroy the deadly foes out to promote heavy-handed havoc across the board.\r\n\r\nConveniently, bureaucratic bigwig Waller (through voiceover) introduces the Suicide Squad and describes what beneficial assets they bring to the turbulent table. Among the naughty notables include the well-known ace sniper Floyd Lawton/Deadshot as well as legendary lethal joy-boy Joker and his better (or perhaps worst half) in girlfriend Harley Quinn. The other toxic tag-a-longs along for the thrill ride of becoming rebellious rescuers include George Harkness/Boomerang (Jai Courtney), Chato Santana/El Diablo (Jay Hernandez), Waylon Jones/Killer Croc (Adewale Akinnuoye-Agbaje), Tatsu Yamashiro/Katana, Enchantress (Cara Delevingne) and Rick Flag (Joel Kinnaman).\r\n\r\nOverall, **Suicide Squad** is surprisingly depressing and goes through the proverbial motions without so much as taking advantage of its surrealistic makeup. The movie never realizes its excitable potential and drifts into yet another superhero yarn that is more patchy than pronounced. Smith's Deadshot is out in the forefront but for the most part feels restrained and not as spry and savvy as one would imagine. Leto's Joker obviously pales in comparison to the brilliant and mesmerizing psychotic take on the role that earned the late Heath Ledger his posthumous Oscar statuette. In all fairness, nobody could inhabit the Clown Prince of Crime as Ledger uncannily did with committed concentration. Still, Leto's Joker--although viciously off-balance--felt recycled and furiously empty at times. Robbie's turn as Joker's misguided main squeeze merely comes off as a bratty Barbie Doll with synthetic edginess. The other **Squad** participants settle for the back burner more or less which is a crying shame because they should have been more engaged than the tepid material allowed them to be initially.\r\n\r\nWoefully sketchy and missing the fueled opulence that one would expect emerging from this cockeyed costume caper **Suicide Squad** is a detonating dud for the missing explosive DC Comics movie brand that needs to step up the pace if they expect to make a consistent and challenging impression on the devoted fanboys at the box office looking to move beyond the sardonic fantasy-based realm of another redundant serving of a _Batman/Superman_ entry.\r\n\r\n**Suicide Squad** (2016)\r\n\r\nWarner Bros.\r\n\r\n2 hrs. 3 mins.\r\n\r\nStarring: Will Smith, Jared Leto, Margo Robbie, Viola Davis, Joel Kinnaman, Jay Hernandez, Jai Courtney, Scott Eastwood, Adewale Akinnuoye-Agbaje, Ike Barinholtz, Common, Cara Delevinge, Karen Fukuhara, Adam Beach\r\n\r\nDirected and Written by: David Ayer\r\n\r\nMPPA Rating: PG-13\r\n\r\nGenre: Superheroes Saga/Action & Adventure/Comic Book Fantasy\r\n\r\nCritic's rating: ** stars (out of 4 stars)\r\n\r\n(c) **Frank Ochieng** (2016)",
              "url": "https://www.themoviedb.org/review/57a814dc9251415cfb00309a"
            }
          ],
          "total_pages": 1,
          "total_results": 1
        }
     */
    public static List<MovieReview> getMovieReviewsFromTheMovieDbJson(JSONObject TheMovieDbJson)
    {
        List<MovieReview> movieReviews = new ArrayList<>();
        
        if( TheMovieDbJson != null && TheMovieDbJson.has(TheMovieDbCommonJsonFields.RESULTS))
        {
            JSONArray resultsArray = JsonUtil.getJsonArrayFromJsonObj(TheMovieDbJson, TheMovieDbCommonJsonFields.RESULTS);
            if(resultsArray != null)
            {
                for(int i = 0; i < resultsArray.length(); ++i)
                {
                    MovieReview review = getMovieReviewFromMovieJson(JsonUtil.getJsonObjectFromJsonArray(resultsArray, i));
                    movieReviews.add(review);
                }
            }
        }
        return movieReviews;
    }

    private static MovieReview getMovieReviewFromMovieJson(JSONObject movieJson)
    {
        String id;
        String author;
        String content;
        String url;

        MovieReview movieReview = new MovieReview();

        if(movieJson.has(TheMovieDbMovieReviewJsonFields.ID))
        {
            id = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieReviewJsonFields.ID);
            movieReview.setId(id);
        }

        if(movieJson.has(TheMovieDbMovieReviewJsonFields.AUTHOR))
        {
            author = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieReviewJsonFields.AUTHOR);
            movieReview.setId(author);
        }

        if(movieJson.has(TheMovieDbMovieReviewJsonFields.CONTENT))
        {
            content = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieReviewJsonFields.CONTENT);
            movieReview.setId(content);
        }

        if(movieJson.has(TheMovieDbMovieReviewJsonFields.URL))
        {
            url = JsonUtil.getStringFromJsonObj(movieJson, TheMovieDbMovieReviewJsonFields.URL);
            movieReview.setId(url);
        }
        
        return movieReview;
    }
}
