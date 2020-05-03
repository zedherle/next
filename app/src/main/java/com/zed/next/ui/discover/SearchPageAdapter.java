package com.zed.next.ui.discover;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zed.next.R;
import com.zed.next.data.datasource.remote.MoviesModel;
import com.zed.next.domain.model.TopicNextStat;
import com.zed.next.domain.model.UserTopic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchPageAdapter extends RecyclerView.Adapter<SearchPageAdapter.MoviePageViewHolder> {

    private final List<MoviesModel> movieList;
    private Context context;
    public SearchAdapterListener onClickListener;
    private MoviesModel movie;
    private UserTopic userTopic;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public SearchPageAdapter(List<MoviesModel> movieList, Context context, SearchAdapterListener listener) {

        this.movieList = movieList;
        this.context=context;
        this.onClickListener=listener;
    }

    @NonNull
    @Override
    public MoviePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_result_card, parent, false);
        return new MoviePageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePageViewHolder holder, int position) {

        movie = this.movieList.get(position);

        holder.txtTitle.setText(movie.getTitle());
        // holder.txtRating.setText(""+movie.getOverview());
        holder.txtReleaseDate.setText(movie.getReleaseDate());


        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+movie.getPosterPath()).into(holder.ivMovie);

        //  holder.bind(movie, movieClickListener);
    }

    @Override
    public int getItemCount() {

        return this.movieList == null ? 0 : this.movieList.size();

    }

    class MoviePageViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle, txtRating, txtReleaseDate, txtTopicType;
        public Button btNext;
        public ImageView ivMovie;


        public MoviePageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtRating = itemView.findViewById(R.id.txtRating);
            txtReleaseDate = itemView.findViewById(R.id.txtReleaseDate);
            ivMovie = itemView.findViewById(R.id.ivMovie);
         //   txtTopicType=itemView.findViewById(R.id.txtTopicType);

            btNext = itemView.findViewById(R.id.btNext);

            btNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    movie = movieList.get(getAdapterPosition());
                    UserTopic topic = new UserTopic();
                    TopicNextStat topicNextStat = new TopicNextStat();
                    topic.setTopic_id(""+movie.getId());
                    topic.setTopic_title(movie.getTitle());
                    topic.setTopic_type("MOVIES");
                    topic.setTopic_poster(movie.getPosterPath());
                    topic.setTopic_status("NEXT");
                    String created_on = format.format( new Date());
                    topicNextStat.setCreated_on(created_on);
                    topic.setTopicNextStat(topicNextStat);
                    onClickListener.iconButtonOnClick(v, topic);
                }
            });
        }
    }
}


