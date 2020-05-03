package com.zed.next.data.repository;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zed.next.data.datasource.remote.FirestoreCollections;
import com.zed.next.domain.interfaces.Repository;
import com.zed.next.domain.interfaces.Specification;
import com.zed.next.domain.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

public class UserRemoteRepository extends FirestoreCollections implements Repository<UserModel> {

    FirebaseFirestore firestore = FirebaseFirestore.getInstance();
    UserModel userModel;

    @Override
    public MutableLiveData<Boolean> add(UserModel item) {

        MutableLiveData<Boolean> isAdded = new MutableLiveData<Boolean>();
        String id = item.getUid();
        firestore.collection(USER_COLLECTION).document(id).set(item).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                // String id = documentReference.getId();
                isAdded.setValue(true);
                //  onFireStoreTaskComplete.newTopicAdded(id);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
            }
        });
        return isAdded;
    }

    @Override
    public MutableLiveData<Boolean> add(Iterable<UserModel> items) {
        return null;
    }

    @Override
    public MutableLiveData<Boolean> update(UserModel item) {
        return null;
    }

    @Override
    public MutableLiveData<Boolean> remove(UserModel item) {
        return null;
    }

    @Override
    public MutableLiveData<Boolean> remove(Specification specification) {
        return null;
    }

    @Override
    public MutableLiveData<List<UserModel>> query(Specification specification) {

        return null;
    }

    public MutableLiveData<Boolean> isIdExist(String uid) {
        FirebaseFirestore rootRef = FirebaseFirestore.getInstance();
        DocumentReference docIdRef = rootRef.collection(USER_COLLECTION).document(uid);
        MutableLiveData<Boolean> isExist = new MutableLiveData<Boolean>();

        docIdRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        isExist.setValue(true);
                    } else {
                        isExist.setValue(false);
                    }
                } else {

                }
            }
        });
        return isExist;
    }

    public MutableLiveData<List<UserModel>> getUsersByQuery(String uid) {

        MutableLiveData<List<UserModel>> userList = new MutableLiveData<List<UserModel>>();
        List<UserModel> userModelList = new ArrayList<>();

        firestore.collection(USER_COLLECTION).whereEqualTo("fname", uid).get().addOnCompleteListener(
                new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                userModel = document.toObject(UserModel.class);
                                userModelList.add(userModel);
                            }
                            if (userList != null) {
                                // onFireStoreTaskComplete.onTopicsReceived(userTopicList);
                                Log.w("TEST", "I am here", task.getException());
                                userList.setValue(userModelList);
                            }

                        } else {
                            Log.w("TEST", "Error getting documents.", task.getException());
                           // onFireStoreTaskComplete.onTopicsReceived(userTopicList);
                        }

                    }
                }
        );

        return userList;
    }


}
