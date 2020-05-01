package com.zed.next.data.datasource.services.api;

import android.text.TextUtils;

import com.zed.next.data.datasource.remote.MoviesModel;

public class MoviesResponse {

    public MoviesModel[] Search;
    public String Response; // True | False
    public String Error;

    public boolean success() {
        return Response != null && Response.equals("True");
    }

    public String error() {
        return success() ? null : TextUtils.isEmpty(Error) ? "Unknown error" : Error;
    }
}
