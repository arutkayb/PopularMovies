package nd.centertableinc.popularmovies1.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import nd.centertableinc.popularmovies1.R;
import nd.centertableinc.popularmovies1.activity.RecyclerViewContainer;
import nd.centertableinc.popularmovies1.data.movie.MovieItem;
import nd.centertableinc.popularmovies1.data.movie.MovieReview;
import nd.centertableinc.popularmovies1.data.utils.MovieUtil;

/**
 * Created by Rutkay on 04.03.2018.
 */

public class MovieReviewsAdapter extends RecyclerView.Adapter<MovieReviewsAdapter.MovieReviewsViewHolder>{
    private Context context;
    private RecyclerViewContainer recyclerViewContainer;
    private List<MovieReview> movieReviews;

    public MovieReviewsAdapter(Context context, RecyclerViewContainer recyclerViewContainer, List<MovieReview> movieReviews)
    {
        this.context = context;
        this.movieReviews = movieReviews;
        this.recyclerViewContainer = recyclerViewContainer;

    }

    @Override
    public MovieReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_review_card_item, parent, false);

        return new MovieReviewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieReviewsViewHolder holder, final int position) {
        MovieReview movieReview = movieReviews.get(position);
        holder.bindHolder(movieReview);

        if(position == movieReviews.size() - 1)
        {
            recyclerViewContainer.itemHitListener(movieReviews.size());
        }
    }

    @Override
    public int getItemCount() {
        return movieReviews.size();
    }

    public class MovieReviewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        TextView author;
        TextView content;
        TextView url;

        @Override
        public void onClick(View view) {
            recyclerViewContainer.onCustomClickListener(getAdapterPosition());
        }

        public MovieReviewsViewHolder(View view)
        {
            super(view);

            author = view.findViewById(R.id.author);
            content = view.findViewById(R.id.content);
            url = view.findViewById(R.id.url);

            view.setOnClickListener(this);
        }

        public void bindHolder(MovieReview movieReview)
        {
            String titleString = context.getString(R.string.author) + ": " + String.valueOf(movieReview.getAuthor());
            author.setText(titleString);

            String contentString = context.getString(R.string.content) + ": " + String.valueOf(movieReview.getContent());
            content.setText(contentString);

            String urlString = context.getString(R.string.url) + ": " + String.valueOf(movieReview.getUrl());
            url.setText(urlString);

        }

    }
}
