package com.zed.next.domain.usecases;

import com.zed.next.data.repository.MoviesRepositoryImpl;
import com.zed.next.domain.Usecase;

public class GetMovies extends Usecase<GetMovies.RequestValues, GetMovies.ResponseValues> {


    MoviesRepositoryImpl moviesRepository = null;

    @Override
    protected void executeUseCase(RequestValues requestValues) {

        moviesRepository = new MoviesRepositoryImpl();
        moviesRepository.getMoviesById("id");

    }

    public static final class RequestValues implements Usecase.RequestValues
    {
        public final String uid;

        public RequestValues(String uid) {
            this.uid = uid;
        }

    }

    public static final class ResponseValues implements Usecase.ResponseValue
    {
//        public final List<MoviesModel> moviesModels;
//        public ResponseValue(@NonNull List<MoviesModel> moviesModels) {
//           // mTasks = checkNotNull(tasks, "tasks cannot be null!");
        }
    }

