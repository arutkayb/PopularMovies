package nd.centertableinc.popularmovies1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by Rutkay on 04.03.2018.
 */

public class MovieOverviewAdapter extends RecyclerView.Adapter<MovieOverviewAdapter.MoviesOverviewViewHolder>{
    int mNumberItems;
    private Context context;
    private List<MovieItem> movieItems;

    public MovieOverviewAdapter(List<MovieItem> movieItems, Context context)
    {
        this.context = context;
        this.movieItems = movieItems;
    }

    @Override
    public MoviesOverviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.overview_card_item, parent, false);

        return new MoviesOverviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesOverviewViewHolder holder, int position) {
        MovieItem movieItem = movieItems.get(position);
        holder.bindHolder(movieItem);
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public class MoviesOverviewViewHolder extends RecyclerView.ViewHolder
    {

        public MoviesOverviewViewHolder(View view)
        {
            super(view);
        }

        public void bindHolder(MovieItem movieItem)
        {
            //TODO: use movieItem as a data source and fill the view from movieItem model
        }

    }
}
