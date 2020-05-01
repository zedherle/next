package com.zed.next.ui.follow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zed.next.R;
import com.zed.next.domain.model.UserTopic;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class FollowPageAdapter extends RecyclerView.Adapter<FollowPageAdapter.UserDonePageViewHolder> {

    private List<UserTopic> userDoneList;
    private UserTopic userDone;

    public FollowPageAdapter(List<UserTopic> userDoneList){
        this.userDoneList = userDoneList;
    }

    @NonNull
    @Override
    public FollowPageAdapter.UserDonePageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_card, parent, false);
        return new FollowPageAdapter.UserDonePageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FollowPageAdapter.UserDonePageViewHolder holder, int position) {


        userDone = this.userDoneList.get(position);

        holder.txtTitle.setText(userDone.getUser_id());
        holder.txtRating.setText(""+userDone.getTopic_id());
        holder.txtReleaseDate.setText(userDone.getTopic_type());
        //  Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+movie.getPosterPath()).into(holder.ivMovie);

    }

    @Override
    public int getItemCount() {

        return this.userDoneList == null ? 0 : this.userDoneList.size();
    }

    class UserDonePageViewHolder extends RecyclerView.ViewHolder{

        public TextView txtTitle, txtRating, txtReleaseDate;
        public Button btDone;
        public ImageView ivMovie;


        public UserDonePageViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtRating = itemView.findViewById(R.id.txtRating);
            txtReleaseDate = itemView.findViewById(R.id.txtReleaseDate);
            ivMovie = itemView.findViewById(R.id.ivMovie);
            btDone = itemView.findViewById(R.id.btDone);

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






