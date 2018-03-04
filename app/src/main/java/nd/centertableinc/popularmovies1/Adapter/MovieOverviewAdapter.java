package nd.centertableinc.popularmovies1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.OverviewItem;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by Rutkay on 04.03.2018.
 */

public class MovieOverviewAdapter extends RecyclerView.Adapter<MovieOverviewAdapter.MoviesOverviewViewHolder>{
    int mNumberItems;
    private Context context;
    private List<OverviewItem> overviewItems;

    public MovieOverviewAdapter(List<OverviewItem> overviewItems, Context context)
    {
        this.context = context;
        this.overviewItems = overviewItems;
    }

    @Override
    public MoviesOverviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.overview_card_item, parent, false);

        return new MoviesOverviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesOverviewViewHolder holder, int position) {
        OverviewItem overviewItem = overviewItems.get(position);
        holder.bindHolder(overviewItem);
    }

    @Override
    public int getItemCount() {
        return overviewItems.size();
    }

    public class MoviesOverviewViewHolder extends RecyclerView.ViewHolder
    {

        public MoviesOverviewViewHolder(View view)
        {
            super(view);
        }

        public void bindHolder(OverviewItem overviewItem)
        {
            //TODO: use overviewItem as a data source and fill the view from overviewItem model
        }

    }
}
