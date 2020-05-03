package com.zed.next.ui.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zed.next.R;
import com.zed.next.domain.model.UserModel;
import com.zed.next.domain.model.UserTopic;
import com.zed.next.ui.discover.SearchAdapterListener;

import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SearchUserAdapter extends RecyclerView.Adapter<SearchUserAdapter.MoviePageViewHolder> {

    private final List<UserModel> userModels;
    private Context context;
    public SearchAdapterListener onClickListener;
    private UserModel user;
    private UserTopic userTopic;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");



    public SearchUserAdapter(List<UserModel> userModels, Context context, SearchAdapterListener listener) {

        this.userModels = userModels;
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

        user = this.userModels.get(position);

        holder.txtTitle.setText(user.getFname()+user.getLname());
        // holder.txtRating.setText(""+movie.getOverview());
        holder.txtReleaseDate.setText(user.getCreated_on());
        //     holder.txtTopicType.setText("MOVIE");
        Glide.with(context).load(user.getImgPath()).into(holder.ivMovie);

        //  holder.bind(movie, movieClickListener);
    }

    @Override
    public int getItemCount() {

        return this.userModels == null ? 0 : this.userModels.size();

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

                }
            });
        }
    }
}


