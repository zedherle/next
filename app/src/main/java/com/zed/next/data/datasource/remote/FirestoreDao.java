package com.zed.next.data.datasource.remote;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.ListenerRegistration;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zed.next.domain.model.UserDone;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class FirestoreDao extends FirestoreCollections{


    private static final String TAG = FirestoreDao.class.getSimpleName();
    private Context context;
    protected FirebaseFirestore firestore;
    private ArrayList<UserDone> userDoneList;
    private UserDone userDone = null;
    private FirestoreCallbacks.LoadUsers firestoreDatasourceCallbacks = null;
    private Query collectionQuery = null;
    private ListenerRegistration registration = null;

    public FirestoreDao() {
        firestore = FirestoreDatasource.getInstance();
    }
    public FirestoreDao(Context context) {
        this.context = context;
        firestore = FirestoreDatasource.getInstance();

    }
    public void registerCallBack(FirestoreCallbacks.LoadUsers listner) {
        this.firestoreDatasourceCallbacks = listner;
    }

    public void unregisterCallBack(FirestoreCallbacks.LoadUsers listner) {
        registration.remove();
    }

    public void getUserDone(String uid) {
        userDoneList = new ArrayList<UserDone>();

        publishEvents(USER_DONE_COLLECTION);

//            firestore.collection(USER_DONE_COLLECTION)
//                    .get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                            if (task.isSuccessful()) {
//                                for (QueryDocumentSnapshot document : task.getResult()) {
//
//                                    userDone = document.toObject(UserDone.class);
//                                    userDoneList.add(userDone);
//
//                                }
//                                firestoreDatasourceCallbacks.onSuccess(userDoneList);
//                                publishEvents(USER_DONE_COLLECTION);
//                            } else {
//                                Log.w("TEST", "Error getting documents.", task.getException());
//                            }
//
//                        }
//                    });

    }

    private void publishEvents(String collectionName) {
        collectionQuery = firestore.collection(collectionName);
        registration = collectionQuery.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(QuerySnapshot queryDocumentSnapshots, FirebaseFirestoreException e) {

                if (e != null) {
                    Log.w("TAG", "listen:error", e);
                    return;
                }

                for (DocumentChange dc : queryDocumentSnapshots.getDocumentChanges()) {
                    switch (dc.getType()) {
                        case ADDED:
                            Log.d("TAG", "New Msg: " + dc.getDocument().toObject(UserDone.class));
                            userDone = dc.getDocument().toObject(UserDone.class);
                            userDoneList.add(userDone);
                            // getUserDone("");
                            break;
                        case MODIFIED:
                            Log.d("TAG", "Modified Msg: " + dc.getDocument().toObject(UserDone.class));

                            break;
                        case REMOVED:
                            Log.d("TAG", "Removed Msg: " + dc.getDocument().toObject(UserDone.class));
                            userDone = dc.getDocument().toObject(UserDone.class);
                            userDoneList.remove(userDone);
                            break;
                    }
                }

                firestoreDatasourceCallbacks.onSuccess(userDoneList);
            }
        });
    }

    public void getDoneById(String uid) {
        userDoneList = new ArrayList<UserDone>();
        Log.w("TEST", "Get Done By ID." + uid);

        firestore.collection(USER_DONE_COLLECTION).whereEqualTo("user_id", uid)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {

                                userDone = document.toObject(UserDone.class);
                                userDoneList.add(userDone);
                            }
                            if (userDoneList != null) {
                                firestoreDatasourceCallbacks.onSuccess(userDoneList);
                            }
                            // publishEvents(USER_DONE_COLLECTION);
                        } else {
                            Log.w("TEST", "Error getting documents.", task.getException());
                        }

                    }
                });

    }


}
