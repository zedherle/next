package com.zed.next.data.datasource.remote;

import com.zed.next.domain.model.UserDone;

import java.util.List;

public interface FirestoreCallbacks {

    interface LoadUsers {
        void onSuccess(List<UserDone> userDones);
        void onError();
    }

    interface Response{

        void onSuccess();
        void onError();
    }

}
