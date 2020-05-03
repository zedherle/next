package com.zed.next.ui.profile;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zed.next.R;


public class SearchCursorAdapter extends CursorAdapter {


    public TextView txtTitle, txtRating, txtReleaseDate, txtTopicType;
    public Button btNext;
    public ImageView ivMovie;

    public SearchCursorAdapter(Context context, Cursor c, boolean autoRequery) {
        super(context, c, autoRequery);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
        return LayoutInflater.from(context).inflate(R.layout.movie_result_card, viewGroup, false);    }

    @Override
    public void bindView(View itemView, Context context, Cursor cursor) {

        txtTitle = itemView.findViewById(R.id.txtTitle);
        txtRating = itemView.findViewById(R.id.txtRating);
        txtReleaseDate = itemView.findViewById(R.id.txtReleaseDate);
        ivMovie = itemView.findViewById(R.id.ivMovie);

    }
}
