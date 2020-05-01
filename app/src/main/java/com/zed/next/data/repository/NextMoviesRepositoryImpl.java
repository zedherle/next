package com.zed.next.data.repository;

import android.util.Log;

import com.zed.next.data.datasource.remote.MoviesModel;
import com.zed.next.data.datasource.services.api.APIClient;
import com.zed.next.data.datasource.services.api.APIService;
import com.zed.next.data.datasource.services.api.MovieResponse;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NextMoviesRepositoryImpl  {

    private static NextMoviesRepositoryImpl instance;
    private ArrayList<MoviesModel> dataset = new ArrayList<>();
    private APIService apiService;
    private static final String TAG = NextMoviesRepositoryImpl.class.getSimpleName();

    public static NextMoviesRepositoryImpl getInstance()
    {
        if(instance == null)
        {
            instance = new NextMoviesRepositoryImpl();

        }

        return instance;
    }

    public MutableLiveData<List<MoviesModel>> getNextMovies()
    {
        apiService = APIClient.getClient();

        final MutableLiveData<List<MoviesModel>>data = new MutableLiveData<>();

        apiService.getMovies(1, "df0009b053c2106b9e28a06fab0daedf").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                Log.d(TAG, "onResponse response:: " + response);

                if (response.body() != null) {

                    data.setValue(response.body().getResults());

                }

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }

    public MutableLiveData<List<MoviesModel>> getMoviesByQuery(String query)
    {
        apiService = APIClient.getClient();

        final MutableLiveData<List<MoviesModel>>data = new MutableLiveData<>();

        apiService.getMoviesBasedOnQuery("df0009b053c2106b9e28a06fab0daedf", query).enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                Log.d(TAG, "onResponse response:: " + response);

                if (response.body() != null) {

                    data.setValue(response.body().getResults());

                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                data.setValue(null);

            }
        });


        return data;
    }


    public MutableLiveData<Boolean> markItDone()
    {
        MutableLiveData<Boolean> isDone = new MutableLiveData<>();


        return isDone;
    }
}
