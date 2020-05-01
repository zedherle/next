package com.zed.next.ui.follow;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.gson.Gson;
import com.zed.next.MainActivity;
import com.zed.next.R;
import com.zed.next.data.datasource.remote.FirestoreDao;
import com.zed.next.domain.model.UserDone;
import com.zed.next.domain.model.UserTopic;
import com.zed.next.ui.common.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FollowFragment extends Fragment{

    View view = null;
    private Gson gson;
    private FirestoreDao firestoreDao;
    private Button btnNewDone;
    private RecyclerView recyclerView;
    private FollowPageAdapter userDoneAdapter;
    List<UserDone> userDones;
    private FollowViewModel followViewModel;
    String message;
    private List<UserTopic> al = new ArrayList<>();

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        followViewModel =
                ViewModelProviders.of(this).get(FollowViewModel.class);

        Intent intent = getActivity().getIntent();
        message= ""+intent.getStringExtra(MainActivity.CURRENT_USER);

        view = inflater.inflate(R.layout.fragment_notifications, container, false);
        btnNewDone = view.findViewById(R.id.btnNewDone);

        recyclerView = view.findViewById(R.id.recyclerView);

        initRecyclerView();

        followViewModel.getUserDoneTopics(message, "NEXT");

        followViewModel.getmUserDoneTopics().observe(this, new Observer<List<UserTopic>>() {
            @Override
            public void onChanged(List<UserTopic> userTopics) {
                al.addAll(userTopics);
                userDoneAdapter.notifyDataSetChanged();
            }
        });

        return view;

    }

    private void initRecyclerView() {


        userDoneAdapter  = new FollowPageAdapter(al);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(new LinearLayoutManager(getContext())) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if ((page + 1) <= 2) {
                    // loadPage(page + 1);
                }
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.setAdapter(userDoneAdapter);


    }
}
