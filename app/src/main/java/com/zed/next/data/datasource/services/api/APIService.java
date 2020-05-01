package com.zed.next.data.datasource.services.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET("/?type=movie&r=json")
    Call<MoviesResponse> findMovie(@Query("s") String text);

    @GET("/?type=movie&r=json&plot=full&tomatoes=true")
    Call<MovieResponse> getMovie(@Query("i") String imdb_id);

    @GET("discover/movie")
    Call<MovieResponse> getMovies(@Query("page") int page, @Query("api_key") String api_key);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("page") int page, @Query("api_key") String api_key);

    @GET("search/movie")
    Call<MovieResponse> getMoviesBasedOnQuery(@Query("api_key") String api_key, @Query("query") String q);
}
