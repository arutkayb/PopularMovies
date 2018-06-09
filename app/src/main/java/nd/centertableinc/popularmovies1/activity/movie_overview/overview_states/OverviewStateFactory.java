package nd.centertableinc.popularmovies1.activity.movie_overview.overview_states;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import nd.centertableinc.popularmovies1.activity.StateFactory;
import nd.centertableinc.popularmovies1.data.MovieDbHighestRated;
import nd.centertableinc.popularmovies1.data.MovieDbPopular;
import nd.centertableinc.popularmovies1.data.MoviesDbFavorite;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by Rutkay on 25.03.2018.
 */

public class OverviewStateFactory implements StateFactory{
    Map<Integer, OverviewState> states;
    public OverviewStateFactory(Context context)
    {
        states = new HashMap<>();
        states.put(R.id.item_most_popular, new PopularMovies(new MovieDbPopular(context)));
        states.put(R.id.item_highest_rated, new HighestRatedMovies(new MovieDbHighestRated(context)));
        states.put(R.id.item_favorites, new FavoriteMovies(new MoviesDbFavorite(context)));
    }

    public OverviewState getState(OverviewStateEnum state)
    {
        return states.get(state.getId());
    }
}
