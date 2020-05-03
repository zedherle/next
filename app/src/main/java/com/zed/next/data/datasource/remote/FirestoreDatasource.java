//package com.zed.next.data.datasource.remote;
//
//import com.google.firebase.firestore.FirebaseFirestore;
//import com.zed.next.domain.interfaces.DatasourceRepository;
//
//public class FirestoreDatasource implements DatasourceRepository.MoviesRepository {
//
//        private static FirebaseFirestore INSTANCE = null;
//        FirebaseFirestore db = FirebaseFirestore.getInstance();
//
//        public FirestoreDatasource() {};
//
//        public static synchronized FirebaseFirestore getInstance() {
//            if (INSTANCE == null) {
//                INSTANCE = FirebaseFirestore.getInstance();
//            }
//            return(INSTANCE);
//        }
//
////    @Override
////    public void getMoviesById(String uid) {
////
////        RemoteMoviesDao remoteMoviesDao = new RemoteMoviesDao();
////        remoteMoviesDao.getMoviesById(uid);
////    }
//
//
//}
