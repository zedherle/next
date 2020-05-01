package com.zed.next.data.datasource.remote;

import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.zed.next.domain.model.UserDone;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class RemoteMoviesDao extends FirestoreDao {

    private ArrayList<UserDone> userDoneList = new ArrayList<UserDone>();
    private UserDone userDone;
    private static final String TAG = FirestoreDao.class.getSimpleName();



    public void getMoviesById(String uid) {

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
                                // firestoreDatasourceCallbacks.onSuccess(userDoneList);
                            }
                        } else {
                            Log.w("TEST", "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    public void markItemAsDone(UserDone userDone)
    {
        Log.d("DONE", "Modified Msg: ");
         firestore.collection(USER_DONE_COLLECTION).add(userDone).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //get the generated city ID
                String id = documentReference.getId();
                //   Toast.makeText(context, "User ID - " + id, Toast.LENGTH_SHORT).show();
                Log.d("DONE", "Modified Msg: " + id);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG, "Error!! " + e.getMessage());
                //   Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                Log.d("NOT DONE", "Modified Msg: " + e);
            }
        });

    }
}
