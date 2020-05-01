package com.zed.next.data.repository;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zed.next.data.datasource.remote.FirestoreCollections;
import com.zed.next.domain.interfaces.Repository;
import com.zed.next.domain.interfaces.Specification;
import com.zed.next.domain.model.UserTopic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;


public class UserTopicRemoteRepository extends FirestoreCollections implements Repository<UserTopic> {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    OnFireStoreTaskComplete onFireStoreTaskComplete = null;
    UserTopic userTopic;

    public UserTopicRemoteRepository(OnFireStoreTaskComplete onFireStoreTaskComplete) {
        this.onFireStoreTaskComplete = onFireStoreTaskComplete;
    }

    @Override
    public MutableLiveData<Boolean> add(UserTopic item) {

        Log.d("DONE", "Modified Msg: ");
        MutableLiveData<Boolean> isAdded = new MutableLiveData<Boolean>();
        String id = firestore.collection(USER_TOPIC_COLLECTION).document().getId();
        item.set_id(id);

        firestore.collection(USER_TOPIC_COLLECTION).document(id).set(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
               // String id = documentReference.getId();
                isAdded.setValue(true);
              //  onFireStoreTaskComplete.newTopicAdded(id);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onFireStoreTaskComplete.onError(e);
            }
        });
        return isAdded;
    }

    @Override
    public MutableLiveData<Boolean> add(Iterable<UserTopic> items) {
        return null;
    }

    @Override
    public MutableLiveData<Boolean> update(UserTopic item) {

        Log.d("DONE", "Modified Msg: ");
        MutableLiveData<Boolean> isAdded = new MutableLiveData<Boolean>();
        Map<String,Object> updates = new HashMap<>();
        updates.put("topic_status", "DONE");
        updates.put("topicDoneStat.created_on", item.getTopicDoneStat().getCreated_on());
        updates.put("topicDoneStat.vote", item.getTopicDoneStat().getVote());
        updates.put("topicDoneStat.medium", item.getTopicDoneStat().getMedium());
        updates.put("topicDoneStat.isFavourite", item.getTopicDoneStat().isFavourite());
      //  firestore.collection(USER_TOPIC_COLLECTION).document(item.get_id()).update("topic_status", "DONE");
        firestore.collection(USER_TOPIC_COLLECTION).document(item.get_id()).update(updates).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                isAdded.setValue(true);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                onFireStoreTaskComplete.onError(e);
            }
        });
        return isAdded;
    }

    @Override
    public MutableLiveData<Boolean> remove(UserTopic item) {
        return null;
    }

    @Override
    public MutableLiveData<Boolean> remove(Specification specification) {
        return null;
    }

    @Override
    public MutableLiveData<List<UserTopic>> query(Specification specification) {
        return null;
    }


    public MutableLiveData<List<UserTopic>> getTopicsByStatus(String uid, String status)
    {

        MutableLiveData<List<UserTopic>> userTopicLiveData = new MutableLiveData<>();
        List<UserTopic> userTopicList = new ArrayList<>();
        firestore.collection(USER_TOPIC_COLLECTION).whereEqualTo("user_id", uid).whereEqualTo("topic_status",status)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                userTopic = document.toObject(UserTopic.class);
                                userTopicList.add(userTopic);
                            }
                            if (userTopicList != null) {
                               // onFireStoreTaskComplete.onTopicsReceived(userTopicList);
                                userTopicLiveData.setValue(userTopicList);
                            }

                        } else {
                            Log.w("TEST", "Error getting documents.", task.getException());
                            onFireStoreTaskComplete.onTopicsReceived(userTopicList);
                        }
                    }
                });

        return userTopicLiveData;
    }


    public interface OnFireStoreTaskComplete{

        void newTopicAdded(String id);
        void onError(Exception e);
        void onTopicsReceived(List<UserTopic> userTopics);

    }
}
