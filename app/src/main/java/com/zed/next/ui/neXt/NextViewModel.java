package com.zed.next.ui.neXt;

import android.util.Log;

import com.zed.next.data.repository.UserTopicRemoteRepository;
import com.zed.next.domain.model.UserTopic;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NextViewModel extends ViewModel {

    private MutableLiveData<Boolean> isTopicDone = new MutableLiveData<>();
    private MutableLiveData<List<UserTopic>> mUserNextTopics;
    private UserTopicRemoteRepository userTopicRemoteRepository;
    private UserTopic userTopic;

    public LiveData<List<UserTopic>> getUserNextTopics(String uid)
    {
        if(mUserNextTopics!=null)
        {
            return mUserNextTopics;
        }
        userTopicRemoteRepository = new UserTopicRemoteRepository(new UserTopicRemoteRepository.OnFireStoreTaskComplete() {
            @Override
            public void newTopicAdded(String id) {

            }
            @Override
            public void onError(Exception e) {

            }
            @Override
            public void onTopicsReceived(List<UserTopic> userTopics) {
                mUserNextTopics.setValue(userTopics);

            }
        });
        mUserNextTopics = userTopicRemoteRepository.getTopicsByStatus(uid, "NEXT");
        return mUserNextTopics;
    }

    public LiveData<Boolean> topicDone(String user_id, UserTopic userTopic)
    {
        this.userTopic = userTopic;
        userTopicRemoteRepository = new UserTopicRemoteRepository(new UserTopicRemoteRepository.OnFireStoreTaskComplete() {
            @Override
            public void newTopicAdded(String id) {
                     isTopicDone.setValue(true);
            }

            @Override
            public void onError(Exception e) {
                Log.d("i am here", ""+e);

            }
            @Override
            public void onTopicsReceived(List<UserTopic> userTopics) {
            }
        });
       userTopicRemoteRepository.update(userTopic);
       return isTopicDone;
    }

    public LiveData<Boolean> topicDelete(String user_id, UserTopic userTopic)
    {
        this.userTopic = userTopic;
        userTopicRemoteRepository = new UserTopicRemoteRepository(new UserTopicRemoteRepository.OnFireStoreTaskComplete() {
            @Override
            public void newTopicAdded(String id) {
                isTopicDone.setValue(true);
            }
            @Override
            public void onError(Exception e) {
            }
            @Override
            public void onTopicsReceived(List<UserTopic> userTopics) {
            }
        });
        isTopicDone=userTopicRemoteRepository.remove(userTopic);
        return isTopicDone;
    }

}