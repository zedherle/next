package com.zed.next.ui.profile;


import com.zed.next.data.repository.UserRemoteRepository;
import com.zed.next.data.repository.UserTopicRemoteRepository;
import com.zed.next.domain.model.UserModel;
import com.zed.next.domain.model.UserTopic;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ProfileViewModel extends ViewModel {

    private MutableLiveData<Boolean> isTopicDone = new MutableLiveData<>();
    private MutableLiveData<List<UserModel>> userList;
    private UserTopicRemoteRepository userTopicRemoteRepository;
    private UserRemoteRepository userRemoteRepository;
    private UserTopic userTopic;
    private UserModel userModel;

    public ProfileViewModel() {
        userRemoteRepository = new UserRemoteRepository();
    }

    public LiveData<List<UserModel>> getUsersByQuery(String s)
    {
        userList = userRemoteRepository.getUsersByQuery(s);
        return userList;
    }
}