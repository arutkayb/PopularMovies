package nd.centertableinc.popularmovies1.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import nd.centertableinc.popularmovies1.Activity.MovieOverviewActivity;
import nd.centertableinc.popularmovies1.Activity.RecyclerViewContainer;
import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by Rutkay on 04.03.2018.
 */

public class MovieOverviewAdapter extends RecyclerView.Adapter<MovieOverviewAdapter.MoviesOverviewViewHolder>{
    int mNumberItems;
    private RecyclerViewContainer context;
    private List<MovieItem> movieItems;

    public MovieOverviewAdapter(RecyclerViewContainer context, List<MovieItem> movieItems)
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

    public class MoviesOverviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView title;
        TextView voteAverage;
        TextView overview;

        @Override
        public void onClick(View view) {
            context.onCustomClickListener(getAdapterPosition());
        }

        public MoviesOverviewViewHolder(View view)
        {
            super(view);
            title = view.findViewById(R.id.title_text_view);
            voteAverage = view.findViewById(R.id.vote_average_text_view);
            overview = view.findViewById(R.id.overview_text_view);

            view.setOnClickListener(this);
        }

        public void bindHolder(MovieItem movieItem)
        {
            title.setText(movieItem.getTitle());
            voteAverage.setText(String.valueOf(movieItem.getVoteAverage()));
            overview.setText(movieItem.getOverview());
        }

    }
}
