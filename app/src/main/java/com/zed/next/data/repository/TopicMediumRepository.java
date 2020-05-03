package com.zed.next.data.repository;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zed.next.data.datasource.remote.FirestoreCollections;
import com.zed.next.domain.interfaces.Repository;
import com.zed.next.domain.interfaces.Specification;
import com.zed.next.domain.model.TopicMedium;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class TopicMediumRepository extends FirestoreCollections implements Repository<TopicMedium> {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    TopicMedium topicMedium;

    @Override
    public MutableLiveData<Boolean> add(TopicMedium item) {
        return null;
    }

    @Override
    public MutableLiveData<Boolean> add(Iterable<TopicMedium> items) {
        return null;
    }

    @Override
    public MutableLiveData<Boolean> update(TopicMedium item) {
        return null;
    }

    @Override
    public MutableLiveData<Boolean> remove(TopicMedium item) {
        return null;
    }

    @Override
    public MutableLiveData<Boolean> remove(Specification specification) {
        return null;
    }

    @Override
    public MutableLiveData<List<TopicMedium>> query(Specification specification) {
        return null;
    }

    public MutableLiveData<List<TopicMedium>> getAllMedium(String topicType) {

        MutableLiveData<List<TopicMedium>> topicMediumLive = new MutableLiveData<>();
        List<TopicMedium> topicMediumList = new ArrayList<>();

        firestore.collection(MEDIUM_COLLECTION).whereEqualTo("type", topicType).get().addOnCompleteListener
                (new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                topicMedium = document.toObject(TopicMedium.class);
                                topicMediumList.add(topicMedium);
                            }
                            if (topicMediumList != null) {
                                // onFireStoreTaskComplete.onTopicsReceived(userTopicList);
                                topicMediumLive.setValue(topicMediumList);
                            }
                        } else {
                            Log.w("TEST", "Error getting documents.", task.getException());
                            // onFireStoreTaskComplete.onTopicsReceived(userTopicList);
                        }
                    }
                });
        return topicMediumLive;
    }

}
