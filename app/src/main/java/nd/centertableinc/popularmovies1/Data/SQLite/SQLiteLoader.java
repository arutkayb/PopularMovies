package nd.centertableinc.popularmovies1.Data.SQLite;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;
import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;

public class SQLiteLoader extends AsyncTaskLoader<List<MovieItem>>{

    public SQLiteLoader(Context context) {
        super(context);
    }

    @Override
    public List<MovieItem> loadInBackground() {
        return null;
    }


}
