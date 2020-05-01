package com.zed.next.ui.profile;


import com.zed.next.data.repository.UserTopicRemoteRepository;
import com.zed.next.domain.model.UserTopic;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<Boolean> isTopicDone = new MutableLiveData<>();
    private MutableLiveData<List<UserTopic>> mUserNextTopics;
    private UserTopicRemoteRepository userTopicRemoteRepository;
    private UserTopic userTopic;

}