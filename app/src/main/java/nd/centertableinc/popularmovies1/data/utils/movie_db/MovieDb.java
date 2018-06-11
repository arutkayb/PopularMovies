package nd.centertableinc.popularmovies1.data.utils.movie_db;

import android.content.Context;

import nd.centertableinc.popularmovies1.activity.AsyncDataListener;

public interface MovieDb extends AsyncDataListener{
    Context getContext();
    AsyncDataListener getListener();
    void setListener(AsyncDataListener listener);
}
