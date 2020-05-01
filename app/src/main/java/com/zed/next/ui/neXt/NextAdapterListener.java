package com.zed.next.ui.neXt;

import android.view.View;

import com.zed.next.domain.model.UserTopic;

public interface NextAdapterListener {

    void iconButtonOnClick(View v, UserTopic position);
    void iconImageViewOnClick(View v, UserTopic topic);
}
