package com.zed.next.ui.done;

import com.zed.next.data.repository.UserTopicRemoteRepository;
import com.zed.next.domain.model.UserTopic;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DoneViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<List<UserTopic>> mUserDoneTopics;

    private UserTopicRemoteRepository userTopicRemoteRepository;

    public LiveData<List<UserTopic>> getmUserDoneTopics() {
        return mUserDoneTopics;
    }

    public DoneViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");

    }

    public LiveData<String> getText() {
        return mText;
    }

    public void getUserDoneTopics(String uid, String status)
    {

        if(mUserDoneTopics!=null)
        {
            return ;
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
                mUserDoneTopics.setValue(userTopics);

            }
        });

        mUserDoneTopics = userTopicRemoteRepository.getTopicsByStatus(uid, "DONE");
    }

}