//package com.zed.next.data.repository;
//
//import com.zed.next.data.datasource.remote.RemoteMoviesDao;
//import com.zed.next.domain.model.UserDone;
//import com.zed.next.domain.interfaces.Repository;
//import com.zed.next.domain.interfaces.Specification;
//
//import java.util.List;
//
//public class DoneTopicFirebaseRepository implements Repository<UserDone> {
//
//    RemoteMoviesDao remoteMoviesDao = new RemoteMoviesDao();
//
//    @Override
//    public void add(UserDone item) {
//
//        remoteMoviesDao.markItemAsDone(item);
//
//    }
//
//    @Override
//    public void add(Iterable<UserDone> items) {
//
//    }
//
//    @Override
//    public void update(UserDone item) {
//
//    }
//
//    @Override
//    public void remove(UserDone item) {
//
//    }
//
//    @Override
//    public void remove(Specification specification) {
//
//    }
//
//    @Override
//    public List<UserDone> query(Specification specification) {
//
//
//        return null;
//    }
//}
