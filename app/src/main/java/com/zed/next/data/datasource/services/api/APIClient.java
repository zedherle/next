package com.zed.next.data.datasource.services.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

  //  private static final String BASE_URL = "http://www.omdbapi.com/?i=tt3896198&apikey=f8c2f73a"; //http://www.omdbapi.com/?i=tt3896198&apikey=f8c2f73a
 //   public static final String API_KEY = "f8c2f73a";


    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "df0009b053c2106b9e28a06fab0daedf";
    private static Retrofit retrofit;
    private static APIService apiClient;

    public void APIClient() {

    }

    public static APIService getClient() {

        if (apiClient == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiClient = retrofit.create(APIService.class);
        }
        return apiClient;
    }
}

