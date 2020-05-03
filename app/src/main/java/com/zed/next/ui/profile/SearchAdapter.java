package com.zed.next.ui.profile;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zed.next.R;
import com.zed.next.domain.model.UserModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class SearchAdapter extends ArrayAdapter {

    Context context = null;
    UserModel user;
    List<UserModel> userModels = null;

    public SearchAdapter(@NonNull Context context, int resourceId, List<UserModel> userModels) {
        super(context, resourceId, userModels);
        this.context = context;
        this.userModels = userModels;

    }

    @Override
    public int getPosition(@Nullable Object item) {
        return super.getPosition(item);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder holder = null;

        Log.w("TEST", "I am here in the list");

        user = userModels.get(position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView==null) {
            convertView = inflater.inflate(R.layout.movie_result_card, null, true);
            holder = new ViewHolder();

            holder.txtTitle = convertView.findViewById(R.id.txtTitle);
            holder.txtRating = convertView.findViewById(R.id.txtRating);
            holder.txtReleaseDate = convertView.findViewById(R.id.txtReleaseDate);
            holder.ivMovie = convertView.findViewById(R.id.ivMovie);

            convertView.setTag(holder);
        }
        else
            holder = (ViewHolder) convertView.getTag();

        holder.txtTitle.setText(user.getFname());
        // holder.txtRating.setText(""+movie.getOverview());
        holder.txtReleaseDate.setText(user.getLname());
        //     holder.txtTopicType.setText("MOVIE");
      //  Glide.with(context).load(user.getImgPath()).into(holder.ivMovie);

        return convertView;
    }


    /*private view holder class*/
    private class ViewHolder {
        public TextView txtTitle, txtRating, txtReleaseDate, txtTopicType;
        public Button btNext;
        public ImageView ivMovie;
    }

    }

