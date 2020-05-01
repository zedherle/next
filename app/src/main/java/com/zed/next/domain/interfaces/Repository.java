package com.zed.next.domain.interfaces;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public interface Repository<T> {

    MutableLiveData<Boolean> add(T item);

    MutableLiveData<Boolean> add(Iterable<T> items);

    MutableLiveData<Boolean> update(T item);

    MutableLiveData<Boolean> remove(T item);

    MutableLiveData<Boolean> remove(Specification specification);

    MutableLiveData<List<T>> query(Specification specification);
}
