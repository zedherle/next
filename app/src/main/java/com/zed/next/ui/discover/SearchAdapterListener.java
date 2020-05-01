package com.zed.next.ui.discover;

import android.view.View;

import com.zed.next.domain.model.UserTopic;

public interface SearchAdapterListener {

        void iconButtonOnClick(View v, UserTopic position);
        void iconImageViewOnClick(View v, UserTopic topic);

}
