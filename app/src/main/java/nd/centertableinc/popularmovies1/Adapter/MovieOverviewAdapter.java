package nd.centertableinc.popularmovies1.Adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import nd.centertableinc.popularmovies1.Data.MovieDb;
import nd.centertableinc.popularmovies1.Data.Utils.MovieItemUtil;
import nd.centertableinc.popularmovies1.Interfaces.RecyclerViewContainer;
import nd.centertableinc.popularmovies1.Data.RecyclerViewItems.MovieItem;
import nd.centertableinc.popularmovies1.R;

/**
 * Created by Rutkay on 04.03.2018.
 */

public class MovieOverviewAdapter extends RecyclerView.Adapter<MovieOverviewAdapter.MoviesOverviewViewHolder>{
    int mNumberItems;
    private Context context;
    private RecyclerViewContainer recyclerViewContainer;
    private List<MovieItem> movieItems;

    public MovieOverviewAdapter(Context context, RecyclerViewContainer recyclerViewContainer, List<MovieItem> movieItems)
    {
        this.context = context;
        this.movieItems = movieItems;
        this.recyclerViewContainer = recyclerViewContainer;

    }

    @Override
    public MoviesOverviewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.overview_card_item, parent, false);

        return new MoviesOverviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MoviesOverviewViewHolder holder, final int position) {
        MovieItem movieItem = movieItems.get(position);
        holder.bindHolder(movieItem);

        if(position == movieItems.size() - 1)
        {
            recyclerViewContainer.lastItemHitListener(movieItems.size());
        }
    }

    @Override
    public int getItemCount() {
        return movieItems.size();
    }

    public class MoviesOverviewViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView poster;
        TextView title;
        TextView voteAverage;
        TextView popularity;

        @Override
        public void onClick(View view) {
            recyclerViewContainer.onCustomClickListener(getAdapterPosition());
        }

        public MoviesOverviewViewHolder(View view)
        {
            super(view);
            poster = view.findViewById(R.id.poster_image_view);
            title = view.findViewById(R.id.title_text_view);
            voteAverage = view.findViewById(R.id.vote_average_text_view);
            popularity = view.findViewById(R.id.popularity_text_view);

            view.setOnClickListener(this);
        }

        public void bindHolder(MovieItem movieItem)
        {
            title.setText(movieItem.getTitle());

            String voteAverageString = context.getString(R.string.vote_average) + ": " + String.valueOf(movieItem.getVoteAverage());
            voteAverage.setText(voteAverageString);

            String popularityString = context.getString(R.string.popularity) + ": " + String.valueOf((int)movieItem.getPopularity());
            popularity.setText(popularityString);

            poster.setContentDescription(movieItem.getTitle());
            String imgUrl = MovieItemUtil.getSmallImageUrlFromImagePath(movieItem.getPosterPath());
            Picasso.with(context).load(imgUrl).into(poster);
        }



    }
}
