package com.zed.next.ui.discover;


import com.zed.next.data.datasource.remote.MoviesModel;
import com.zed.next.data.repository.NextMoviesRepositoryImpl;
import com.zed.next.data.repository.UserTopicRemoteRepository;
import com.zed.next.domain.model.UserTopic;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DiscoverViewModel extends ViewModel {

    private MutableLiveData<List<MoviesModel>> mNextTopics;
    private MutableLiveData<List<MoviesModel>> mMovieResult;
    private MutableLiveData<Boolean> isTopicNextAdded = new MutableLiveData<>();
    private NextMoviesRepositoryImpl nextMoviesRepository = null;
    private UserTopicRemoteRepository userTopicRemoteRepository;
    private UserTopic userTopic;

    public void init() {
        if(mNextTopics!=null)
        {
            return;
        }
        nextMoviesRepository = NextMoviesRepositoryImpl.getInstance();
        mNextTopics = nextMoviesRepository.getNextMovies();
    }

    public LiveData<List<MoviesModel>> getNextTopics()
    {
        return mNextTopics;
    }
    public LiveData<List<MoviesModel>> getMoviesByQuery(String s)
    {
        mMovieResult = nextMoviesRepository.getMoviesByQuery(s);
        return mMovieResult;
    }
    public LiveData<Boolean> topicNext(String user_id, UserTopic userTopic)
    {

        this.userTopic = userTopic;
        userTopic.setUser_id(user_id);

        userTopicRemoteRepository = new UserTopicRemoteRepository(new UserTopicRemoteRepository.OnFireStoreTaskComplete() {
            @Override
            public void newTopicAdded(String id) {
                isTopicNextAdded.setValue(true);
            }

            @Override
            public void onError(Exception e) {

            }
            @Override
            public void onTopicsReceived(List<UserTopic> userTopics) {

            }
        });
        userTopicRemoteRepository.add(userTopic);

        return isTopicNextAdded;
    }
}