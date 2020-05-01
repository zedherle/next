package com.zed.next.ui.done;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zed.next.R;
import com.zed.next.domain.model.UserTopic;
import com.zed.next.ui.common.TimeAgo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class DonePageAdapter extends RecyclerView.Adapter<DonePageAdapter.UserDonePageViewHolder> {

    private List<UserTopic> userDoneList;
    private UserTopic userDone;
    private Context context;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public DonePageAdapter(List<UserTopic> userDoneList, Context context){


        this.userDoneList = userDoneList;
        this.context=context;
    }

    @NonNull
    @Override
    public DonePageAdapter.UserDonePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.topic_done, parent, false);
        return new DonePageAdapter.UserDonePageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DonePageAdapter.UserDonePageViewHolder holder, int position) {

        userDone = this.userDoneList.get(position);

        holder.txtTitle.setText(userDone.getTopic_title());
        //holder.txtRating.setText("Where? :" + userDone.getTopicDoneStat().getMedium());
        String timeAgo = "";
        try {
            timeAgo = TimeAgo.getTimeAgo(format.parse(userDone.getTopicDoneStat().getCreated_on()).getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.txtReleaseDate.setText("Watched "+ timeAgo + " on " +userDone.getTopicDoneStat().getMedium() );
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+userDone.getTopic_poster()).into(holder.ivMovie);

    }

    @Override
    public int getItemCount() {

        return this.userDoneList == null ? 0 : this.userDoneList.size();
    }

    class UserDonePageViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle, txtRating, txtReleaseDate;

        public ImageView ivMovie, ivLike;


        public UserDonePageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtRating = itemView.findViewById(R.id.txtRating);
            txtReleaseDate = itemView.findViewById(R.id.txtReleaseDate);
            ivMovie = itemView.findViewById(R.id.ivMovie);
            ivLike = itemView.findViewById(R.id.ivLike);


//            btDone.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    movie = movieList.get(getAdapterPosition());
//                    onClickListener.iconButtonOnClick(v, movie.getId());
//                }
//            });
        }

    }
}






