package com.zed.next.ui.neXt;

import com.zed.next.data.repository.TopicMediumRepository;
import com.zed.next.data.repository.UserTopicRemoteRepository;
import com.zed.next.domain.model.TopicMedium;
import com.zed.next.domain.model.UserTopic;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DoneDialogViewModel extends ViewModel {

    private MutableLiveData<Boolean> isTopicDone = new MutableLiveData<>();
    private UserTopicRemoteRepository userTopicRemoteRepository;
    private TopicMediumRepository userTopicMediumRepository;
    private UserTopic userTopic;

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
            }
            @Override
            public void onTopicsReceived(List<UserTopic> userTopics) {
            }
        });
        isTopicDone = userTopicRemoteRepository.update(userTopic);
        return isTopicDone;
    }

    public LiveData<List<TopicMedium>> getAllMedium(String topicType)
    {
        userTopicMediumRepository = new TopicMediumRepository();
        return userTopicMediumRepository.getAllMedium(topicType);
    }

}
