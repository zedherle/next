package com.zed.next.ui.neXt;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zed.next.R;
import com.zed.next.domain.model.TopicDoneStat;
import com.zed.next.domain.model.UserTopic;
import com.zed.next.ui.common.TimeAgo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TopicPageAdapter extends RecyclerView.Adapter<TopicPageAdapter.MoviePageViewHolder> {

    private final List<UserTopic> userTopicList;
    private Context context;
    public NextAdapterListener onClickListener;
    private UserTopic userTopic;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public TopicPageAdapter(List<UserTopic> userTopicList, Context context, NextAdapterListener listener) {

        this.userTopicList = userTopicList;
        this.context=context;
        this.onClickListener=listener;
    }

    @NonNull
    @Override
    public MoviePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new MoviePageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviePageViewHolder holder, int position) {

        userTopic = this.userTopicList.get(position);

        holder.txtTitle.setText(userTopic.getTopic_title());
        String timeAgo = "";
        try {
             timeAgo = TimeAgo.getTimeAgo(format.parse(userTopic.getTopicNextStat().getCreated_on()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.txtRating.setText("Added "+timeAgo);
      //  holder.txtReleaseDate.setText(userTopic.getTopic_type());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+userTopic.getTopic_poster()).into(holder.ivMovie);

        //  holder.bind(movie, movieClickListener);
    }

    @Override
    public int getItemCount() {

        return this.userTopicList == null ? 0 : this.userTopicList.size();

    }

    class MoviePageViewHolder extends RecyclerView.ViewHolder {

        public TextView txtTitle, txtRating, txtReleaseDate;
        public Button btDone;
        public ImageView ivMovie;


        public MoviePageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtRating = itemView.findViewById(R.id.txtRating);
            txtReleaseDate = itemView.findViewById(R.id.txtReleaseDate);
            ivMovie = itemView.findViewById(R.id.ivMovie);

            btDone = itemView.findViewById(R.id.btDone);






            btDone.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    userTopic = userTopicList.get(getAdapterPosition());
                    TopicDoneStat topicDoneStat = new TopicDoneStat();
                    topicDoneStat.setFavourite(true);
                    topicDoneStat.setMedium("NETFLIX");
                    topicDoneStat.setVote(10);
                    String created_on = format.format( new Date());
                    topicDoneStat.setCreated_on(created_on);
                    userTopic.setTopicDoneStat(topicDoneStat);

                    onClickListener.iconButtonOnClick(v, userTopic);
                }
            });
        }
    }
}


