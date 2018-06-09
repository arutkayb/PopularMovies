package nd.centertableinc.popularmovies1.activity.movie_overview.overview_states;

import java.util.HashMap;
import java.util.Map;

import nd.centertableinc.popularmovies1.R;

public enum OverviewStateEnum {
    POPULAR_STATE(R.id.item_most_popular),
    HIGHEST_RATED_STATE(R.id.item_highest_rated),
    FAVORITE_STATE(R.id.item_favorites);

    static Map<Integer, OverviewStateEnum> idMap = new HashMap<>();
    static{
        for (OverviewStateEnum e: values())
        {
            idMap.put(e.getId(), e);
        }
    }

    private final int id;
    OverviewStateEnum(int id)
    {
        this.id = id;

    }

    public Integer getId()
    {
        return this.id;
    }

    public static OverviewStateEnum getById(Integer id)
    {
        return idMap.get(id);
    }

}
