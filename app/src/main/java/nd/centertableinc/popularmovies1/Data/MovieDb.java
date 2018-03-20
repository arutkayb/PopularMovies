package nd.centertableinc.popularmovies1.Data;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import nd.centertableinc.popularmovies1.Data.Utils.HttpUtil;
import nd.centertableinc.popularmovies1.Data.Utils.JsonUtil;
import nd.centertableinc.popularmovies1.Interfaces.AsyncDataListener;

/**
 * Created by Rutkay on 04.03.2018.
 */

public class MovieDb {
    private static final String movieDbTestJson = "{\"page\":1,\"total_results\":19798,\"total_pages\":990,\"results\":[{\"vote_count\":6521,\"id\":269149,\"video\":false,\"vote_average\":7.7,\"title\":\"Zootopia\",\"popularity\":397.755132,\"poster_path\":\"\\/sM33SANp9z6rXW8Itn7NnG1GOEs.jpg\",\"original_language\":\"en\",\"original_title\":\"Zootopia\",\"genre_ids\":[16,12,10751,35],\"backdrop_path\":\"\\/mhdeE1yShHTaDbJVdWyTlzFvNkr.jpg\",\"adult\":false,\"overview\":\"Determined to prove herself, Officer Judy Hopps, the first bunny on Zootopia's police force, jumps at the chance to crack her first case - even if it means partnering with scam-artist fox Nick Wilde to solve the mystery.\",\"release_date\":\"2016-02-11\"},{\"vote_count\":2470,\"id\":399055,\"video\":false,\"vote_average\":7.4,\"title\":\"The Shape of Water\",\"popularity\":389.108536,\"poster_path\":\"\\/k4FwHlMhuRR5BISY2Gm2QZHlH5Q.jpg\",\"original_language\":\"en\",\"original_title\":\"The Shape of Water\",\"genre_ids\":[18,14,10749],\"backdrop_path\":\"\\/rgyhSn3mINvkuy9iswZK0VLqQO3.jpg\",\"adult\":false,\"overview\":\"An other-worldly story, set against the backdrop of Cold War era America circa 1962, where a mute janitor working at a lab falls in love with an amphibious man being held captive there and devises a plan to help him escape.\",\"release_date\":\"2017-12-01\"},{\"vote_count\":806,\"id\":337167,\"video\":false,\"vote_average\":6.3,\"title\":\"Fifty Shades Freed\",\"popularity\":375.787087,\"poster_path\":\"\\/jjPJ4s3DWZZvI4vw8Xfi4Vqa1Q8.jpg\",\"original_language\":\"en\",\"original_title\":\"Fifty Shades Freed\",\"genre_ids\":[18,10749],\"backdrop_path\":\"\\/9ywA15OAiwjSTvg3cBs9B7kOCBF.jpg\",\"adult\":false,\"overview\":\"Believing they have left behind shadowy figures from their past, newlyweds Christian and Ana fully embrace an inextricable connection and shared life of luxury. But just as she steps into her role as Mrs. Grey and he relaxes into an unfamiliar stability, new threats could jeopardize their happy ending before it even begins.\",\"release_date\":\"2018-02-07\"},{\"vote_count\":2794,\"id\":284054,\"video\":false,\"vote_average\":7.4,\"title\":\"Black Panther\",\"popularity\":340.655705,\"poster_path\":\"\\/uxzzxijgPIY7slzFvMotPv8wjKA.jpg\",\"original_language\":\"en\",\"original_title\":\"Black Panther\",\"genre_ids\":[28,12,14,878],\"backdrop_path\":\"\\/b6ZJZHUdMEFECvGiDpJjlfUWela.jpg\",\"adult\":false,\"overview\":\"After the events of Captain America: Civil War, King T'Challa returns home to the reclusive, technologically advanced African nation of Wakanda to serve as his country's new leader. However, T'Challa soon finds that he is challenged for the throne from factions within his own country. When two foes conspire to destroy Wakanda, the hero known as Black Panther must team up with C.I.A. agent Everett K. Ross and members of the Dora Milaje, Wakandan special forces, to prevent Wakanda from being dragged into a world war.\",\"release_date\":\"2018-02-13\"},{\"vote_count\":3018,\"id\":354912,\"video\":false,\"vote_average\":7.8,\"title\":\"Coco\",\"popularity\":199.727927,\"poster_path\":\"\\/eKi8dIrr8voobbaGzDpe8w0PVbC.jpg\",\"original_language\":\"en\",\"original_title\":\"Coco\",\"genre_ids\":[12,16,35,10751],\"backdrop_path\":\"\\/askg3SMvhqEl4OL52YuvdtY40Yb.jpg\",\"adult\":false,\"overview\":\"Despite his family’s baffling generations-old ban on music, Miguel dreams of becoming an accomplished musician like his idol, Ernesto de la Cruz. Desperate to prove his talent, Miguel finds himself in the stunning and colorful Land of the Dead following a mysterious chain of events. Along the way, he meets charming trickster Hector, and together, they set off on an extraordinary journey to unlock the real story behind Miguel's family history.\",\"release_date\":\"2017-10-27\"},{\"vote_count\":4829,\"id\":284053,\"video\":false,\"vote_average\":7.4,\"title\":\"Thor: Ragnarok\",\"popularity\":199.241044,\"poster_path\":\"\\/rzRwTcFvttcN1ZpX2xv4j3tSdJu.jpg\",\"original_language\":\"en\",\"original_title\":\"Thor: Ragnarok\",\"genre_ids\":[28,12,14],\"backdrop_path\":\"\\/kaIfm5ryEOwYg8mLbq8HkPuM1Fo.jpg\",\"adult\":false,\"overview\":\"Thor is imprisoned on the other side of the universe and finds himself in a race against time to get back to Asgard to stop Ragnarok, the prophecy of destruction to his homeworld and the end of Asgardian civilization, at the hands of an all-powerful new threat, the ruthless Hela.\",\"release_date\":\"2017-10-25\"},{\"vote_count\":7292,\"id\":198663,\"video\":false,\"vote_average\":7,\"title\":\"The Maze Runner\",\"popularity\":153.283016,\"poster_path\":\"\\/coss7RgL0NH6g4fC2s5atvf3dFO.jpg\",\"original_language\":\"en\",\"original_title\":\"The Maze Runner\",\"genre_ids\":[28,9648,878,53],\"backdrop_path\":\"\\/lkOZcsXcOLZYeJ2YxJd3vSldvU4.jpg\",\"adult\":false,\"overview\":\"Set in a post-apocalyptic world, young Thomas is deposited in a community of boys after his memory is erased, soon learning they're all trapped in a maze that will require him to join forces with fellow “runners” for a shot at escape.\",\"release_date\":\"2014-09-10\"},{\"vote_count\":4245,\"id\":181808,\"video\":false,\"vote_average\":7.2,\"title\":\"Star Wars: The Last Jedi\",\"popularity\":152.294987,\"poster_path\":\"\\/kOVEVeg59E0wsnXmF9nrh6OmWII.jpg\",\"original_language\":\"en\",\"original_title\":\"Star Wars: The Last Jedi\",\"genre_ids\":[28,14,12],\"backdrop_path\":\"\\/c4Dw37VZjBmObmJw9bmt8IDwMZH.jpg\",\"adult\":false,\"overview\":\"Rey develops her newly discovered abilities with the guidance of Luke Skywalker, who is unsettled by the strength of her powers. Meanwhile, the Resistance prepares to do battle with the First Order.\",\"release_date\":\"2017-12-13\"},{\"vote_count\":3421,\"id\":141052,\"video\":false,\"vote_average\":6.4,\"title\":\"Justice League\",\"popularity\":150.174564,\"poster_path\":\"\\/eifGNCSDuxJeS1loAXil5bIGgvC.jpg\",\"original_language\":\"en\",\"original_title\":\"Justice League\",\"genre_ids\":[28,12,14,878],\"backdrop_path\":\"\\/o5T8rZxoWSBMYwjsUFUqTt6uMQB.jpg\",\"adult\":false,\"overview\":\"Fuelled by his restored faith in humanity and inspired by Superman's selfless act, Bruce Wayne and Diana Prince assemble a team of metahumans consisting of Barry Allen, Arthur Curry and Victor Stone to face the catastrophic threat of Steppenwolf and the Parademons who are on the hunt for three Mother Boxes on Earth.\",\"release_date\":\"2017-11-15\"},{\"vote_count\":7611,\"id\":321612,\"video\":false,\"vote_average\":6.8,\"title\":\"Beauty and the Beast\",\"popularity\":134.898478,\"poster_path\":\"\\/tWqifoYuwLETmmasnGHO7xBjEtt.jpg\",\"original_language\":\"en\",\"original_title\":\"Beauty and the Beast\",\"genre_ids\":[10751,14,10749],\"backdrop_path\":\"\\/6aUWe0GSl69wMTSWWexsorMIvwU.jpg\",\"adult\":false,\"overview\":\"A live-action adaptation of Disney's version of the classic tale of a cursed prince and a beautiful young woman who helps him break the spell.\",\"release_date\":\"2017-03-16\"},{\"vote_count\":316,\"id\":401981,\"video\":false,\"vote_average\":6.3,\"title\":\"Red Sparrow\",\"popularity\":129.073856,\"poster_path\":\"\\/vLCogyfQGxVLDC1gqUdNAIkc29L.jpg\",\"original_language\":\"en\",\"original_title\":\"Red Sparrow\",\"genre_ids\":[9648,53],\"backdrop_path\":\"\\/sGzuQYSTwJvLBc2PnuSVLHhuFeh.jpg\",\"adult\":false,\"overview\":\"Prima ballerina Dominika Egorova faces a bleak and uncertain future after she suffers an injury that ends her career. She soon turns to Sparrow School, a secret intelligence service that trains exceptional young people to use their minds and bodies as weapons. Egorova emerges as the most dangerous Sparrow after completing the sadistic training process. As she comes to terms with her new abilities, Dominika meets a CIA agent who tries to convince her that he is the only person she can trust.\",\"release_date\":\"2018-03-01\"},{\"vote_count\":2645,\"id\":353486,\"video\":false,\"vote_average\":6.5,\"title\":\"Jumanji: Welcome to the Jungle\",\"popularity\":125.407226,\"poster_path\":\"\\/bXrZ5iHBEjH7WMidbUDQ0U2xbmr.jpg\",\"original_language\":\"en\",\"original_title\":\"Jumanji: Welcome to the Jungle\",\"genre_ids\":[28,12,35,10751],\"backdrop_path\":\"\\/rz3TAyd5kmiJmozp3GUbYeB5Kep.jpg\",\"adult\":false,\"overview\":\"The tables are turned as four teenagers are sucked into Jumanji's world - pitted against rhinos, black mambas and an endless variety of jungle traps and puzzles. To survive, they'll play as characters from the game.\",\"release_date\":\"2017-12-09\"},{\"vote_count\":3672,\"id\":335984,\"video\":false,\"vote_average\":7.4,\"title\":\"Blade Runner 2049\",\"popularity\":110.615156,\"poster_path\":\"\\/gajva2L0rPYkEWjzgFlBXCAVBE5.jpg\",\"original_language\":\"en\",\"original_title\":\"Blade Runner 2049\",\"genre_ids\":[9648,878,53],\"backdrop_path\":\"\\/mVr0UiqyltcfqxbAUcLl9zWL8ah.jpg\",\"adult\":false,\"overview\":\"Thirty years after the events of the first film, a new blade runner, LAPD Officer K, unearths a long-buried secret that has the potential to plunge what's left of society into chaos. K's discovery leads him on a quest to find Rick Deckard, a former LAPD blade runner who has been missing for 30 years.\",\"release_date\":\"2017-10-04\"},{\"vote_count\":22,\"id\":394823,\"video\":false,\"vote_average\":5,\"title\":\"Robby and Toby's Fantastic Voyager\",\"popularity\":105.534857,\"poster_path\":\"\\/ssCEE0bfj5gawzwZeGfqrGQtU3f.jpg\",\"original_language\":\"de\",\"original_title\":\"Robbi, Tobbi und das Fliewatüüt\",\"genre_ids\":[12,10751],\"backdrop_path\":\"\\/gZF2EfM8ov834pCzRpt1xt01SUy.jpg\",\"adult\":false,\"overview\":\"One day, robot Robby enters into a life of the most creative little boy, Toby. Robby had been separated from his robot parents when his spaceship crashed. Toby decides to offer his help and the two of them become friends.\",\"release_date\":\"2016-12-01\"},{\"vote_count\":2255,\"id\":392044,\"video\":false,\"vote_average\":6.7,\"title\":\"Murder on the Orient Express\",\"popularity\":100.110268,\"poster_path\":\"\\/iBlfxlw8qwtUS0R8YjIU7JtM6LM.jpg\",\"original_language\":\"en\",\"original_title\":\"Murder on the Orient Express\",\"genre_ids\":[80,18,9648],\"backdrop_path\":\"\\/2J283YNxKhxAqHeVegUJ5mzLfGb.jpg\",\"adult\":false,\"overview\":\"Genius Belgian detective Hercule Poirot investigates the murder of an American tycoon aboard the Orient Express train.\",\"release_date\":\"2017-11-03\"},{\"vote_count\":264,\"id\":347882,\"video\":false,\"vote_average\":5.2,\"title\":\"Sleight\",\"popularity\":99.1897,\"poster_path\":\"\\/wridRvGxDqGldhzAIh3IcZhHT5F.jpg\",\"original_language\":\"en\",\"original_title\":\"Sleight\",\"genre_ids\":[18,53,28,878],\"backdrop_path\":\"\\/2SEgJ0mHJ7TSdVDbkGU061tR33K.jpg\",\"adult\":false,\"overview\":\"A young street magician is left to take care of his little sister after his mother's passing and turns to drug dealing in the Los Angeles party scene to keep a roof over their heads. When he gets into trouble with his supplier, his sister is kidnapped and he is forced to rely on both his sleight of hand and brilliant mind to save her.\",\"release_date\":\"2017-04-28\"},{\"vote_count\":487,\"id\":460793,\"video\":false,\"vote_average\":5.9,\"title\":\"Olaf's Frozen Adventure\",\"popularity\":98.20633,\"poster_path\":\"\\/As8WTtxXs9e3cBit3ztTf7zoRmm.jpg\",\"original_language\":\"en\",\"original_title\":\"Olaf's Frozen Adventure\",\"genre_ids\":[12,16,35,10751,14,10402],\"backdrop_path\":\"\\/9K4QqQZg4TVXcxBGDiVY4Aey3Rn.jpg\",\"adult\":false,\"overview\":\"Olaf is on a mission to harness the best holiday traditions for Anna, Elsa, and Kristoff.\",\"release_date\":\"2017-10-27\"},{\"vote_count\":1941,\"id\":396422,\"video\":false,\"vote_average\":6.4,\"title\":\"Annabelle: Creation\",\"popularity\":96.345048,\"poster_path\":\"\\/tb86j8jVCVsdZnzf8I6cIi65IeM.jpg\",\"original_language\":\"en\",\"original_title\":\"Annabelle: Creation\",\"genre_ids\":[27,9648,53],\"backdrop_path\":\"\\/3L5gfIKt2RK9vnCiLgWTAzkhQWC.jpg\",\"adult\":false,\"overview\":\"Several years after the tragic death of their little girl, a dollmaker and his wife welcome a nun and several girls from a shuttered orphanage into their home, soon becoming the target of the dollmaker's possessed creation, Annabelle.\",\"release_date\":\"2017-08-03\"},{\"vote_count\":2078,\"id\":359940,\"video\":false,\"vote_average\":8.2,\"title\":\"Three Billboards Outside Ebbing, Missouri\",\"popularity\":96.033328,\"poster_path\":\"\\/vgvw6w1CtcFkuXXn004S5wQsHRl.jpg\",\"original_language\":\"en\",\"original_title\":\"Three Billboards Outside Ebbing, Missouri\",\"genre_ids\":[80,18],\"backdrop_path\":\"\\/9oRdMkQ6ysUuF7Ufb1oMH5NMDY2.jpg\",\"adult\":false,\"overview\":\"After seven months have passed without a culprit in her daughter's murder case, Mildred Hayes makes a bold move, painting three signs leading into her town with a controversial message directed at Bill Willoughby, the town's revered chief of police. When his second-in-command Officer Jason Dixon, an immature mother's boy with a penchant for violence, gets involved, the battle between Mildred and Ebbing's law enforcement is only exacerbated.\",\"release_date\":\"2017-11-10\"},{\"vote_count\":4414,\"id\":216015,\"video\":false,\"vote_average\":5.3,\"title\":\"Fifty Shades of Grey\",\"popularity\":92.651261,\"poster_path\":\"\\/jV8wnk3Jgz6f7degmT3lHNGI2tK.jpg\",\"original_language\":\"en\",\"original_title\":\"Fifty Shades of Grey\",\"genre_ids\":[18,10749,53],\"backdrop_path\":\"\\/zAd0MjURkOvJynIsqmLMBcICxUt.jpg\",\"adult\":false,\"overview\":\"When college senior Anastasia Steele steps in for her sick roommate to interview prominent businessman Christian Grey for their campus paper, little does she realize the path her life will take. Christian, as enigmatic as he is rich and powerful, finds himself strangely drawn to Ana, and she to him. Though sexually inexperienced, Ana plunges headlong into an affair -- and learns that Christian's true sexual proclivities push the boundaries of pain and pleasure.\",\"release_date\":\"2015-02-11\"}]}";

    /*
    * API themoviedb.org
    * It’s constructed using 3 parts:
    * The base URL will look like: http://image.tmdb.org/t/p/.
    * Then you will need a ‘size’, which will be one of the following:
    *   "w92", "w154", "w185", "w342", "w500", "w780", or "original". For most phones we recommend using “w185”.
    * And finally the poster path returned by the query, in this case “/nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg”
    * Combining these three parts gives us a final url of http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg
    *
    * example request:
    *   http://api.themoviedb.org/3/movie/popular?api_key=599c34d73931abe6918cfd12792fd160
    *
    * example Picasso usage
    *   Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
    */

    public final static String MOVIE_API_URL = "api.themoviedb.org";
    public final static String MOVIE_API_VERSION_3 = "3";
    public final static String TYPE_MOVIE = "movie";
    public final static String TAG_API_KEY = "api_key";
    public final static String SORT_BY = "sort_by";
    public final static String POPULARITY_DESC= "popularity.desc";
    public final static String DISCOVER = "discover";
    public final static String VOTE_AVERAGE_DESC = "vote_average.desc";
    public final static String PAGE = "page";

    public final static String POSTER_BASE_URL = "image.tmdb.org";
    public final static String POSTER_T = "t";
    public final static String POSTER_P = "p";
    public final static String POSTER_W185 = "w185";
    public final static String POSTER_W342 = "w342";
    public final static String POSTER_W500 = "w500";
    public final static String POSTER_W780 = "w780";

    // size can be "w92", "w154", "w185", "w342", "w500", "w780"
    // overall poster link is like: http://image.tmdb.org/t/p/w185//nBNZadXqJSdt05SHLqgT0HuC5Gm.jpg

    private String apiKey;
    private AsyncDataListener asyncDataListener;
    private Context context;
    private HttpUtil httpUtil;

    private int currentPage;

    public MovieDb(Context context, AsyncDataListener asyncDataListener, String apiKey){
        currentPage = 1;
        this.apiKey = apiKey;
        this.asyncDataListener = asyncDataListener;
        this.context = context;

        httpUtil = new HttpUtil(context, asyncDataListener);
    }

    public void requestForTheMostPopularMovies()
    {
        requestForTheMostPopularMovies(1);
    }

    public void requestForTheMostPopularMovies(int page)
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(MOVIE_API_URL)
                .appendPath(MOVIE_API_VERSION_3)
                .appendPath(DISCOVER)
                .appendPath(TYPE_MOVIE)
                .appendQueryParameter(PAGE, String.valueOf(page))
                .appendQueryParameter(TAG_API_KEY, apiKey)
                .appendQueryParameter(SORT_BY, POPULARITY_DESC);

        String popularMoviesUrl = builder.build().toString();

        try {
            httpUtil.getRequest(popularMoviesUrl);
        }catch (IOException ex)
        {
            Log.e("MovieDb", "requestForTheMostPopularMovies IO Exception: " + ex.toString());
        }

        currentPage = page;
    }

    public void requestForTheHighestRatedMovies()
    {
        requestForTheHighestRatedMovies(1);
    }

    public void requestForTheHighestRatedMovies(int page)
    {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .authority(MOVIE_API_URL)
                .appendPath(MOVIE_API_VERSION_3)
                .appendPath(DISCOVER)
                .appendPath(TYPE_MOVIE)
                .appendQueryParameter(PAGE, String.valueOf(page))
                .appendQueryParameter(TAG_API_KEY, apiKey)
                .appendQueryParameter(SORT_BY, VOTE_AVERAGE_DESC);

        String popularMoviesUrl = builder.build().toString();

        try {
            httpUtil.getRequest(popularMoviesUrl);
        }catch (IOException ex)
        {
            Log.e("MovieDb", "requestForTheMostPopularMovies IO Exception: " + ex.toString());
        }

        currentPage = page;
    }

    public int getCurrentPage()
    {
        return currentPage;
    }


}
