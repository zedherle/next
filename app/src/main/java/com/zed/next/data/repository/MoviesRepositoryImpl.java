package com.zed.next.data.repository;

import com.google.firebase.firestore.FirebaseFirestore;
import com.zed.next.data.datasource.remote.FirestoreCollections;
import com.zed.next.data.datasource.remote.FirestoreDatasource;
import com.zed.next.domain.interfaces.DatasourceRepository;

public class MoviesRepositoryImpl extends FirestoreCollections implements DatasourceRepository.MoviesRepository {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirestoreDatasource firestoreDatasource = new FirestoreDatasource();
   // private ArrayList<UserDone> userDoneList = null;
   // private UserDone userDone = null;

    public MoviesRepositoryImpl() {

    }

    @Override
    public void getMoviesById(String uid) {

            // fetch datasource based on remote or local or any
            firestoreDatasource.getMoviesById(uid);
//            userDoneList = new ArrayList<UserDone>();
//            firestoreDatasource.getMoviesById(uid);

//
//
//            db.collection(USER_DONE_COLLECTION).whereEqualTo("user_id", uid)
//                    .get()
//                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                        @Override
//                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
//
//                            if (task.isSuccessful()) {
//                                for (QueryDocumentSnapshot document : task.getResult()) {
//                                    userDone = document.toObject(UserDone.class);
//                                    userDoneList.add(userDone);
//                                }
//                                if (userDoneList != null) {
//
//                                   // firestoreDatasourceCallbacks.onSuccess(userDoneList);
//                                }
//                            } else {
//                                Log.w("TEST", "Error getting documents.", task.getException());
//                            }
//
//                        }
//                    });

        }
    }

