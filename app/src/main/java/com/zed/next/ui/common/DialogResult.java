package com.zed.next.ui.common;

public interface DialogResult<T> {

    void onSubmit(T result);
    void onCancel();
}
