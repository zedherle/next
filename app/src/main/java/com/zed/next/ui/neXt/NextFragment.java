package com.zed.next.ui.neXt;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.Snackbar;
import com.zed.next.MainActivity;
import com.zed.next.R;
import com.zed.next.domain.model.TopicDoneStat;
import com.zed.next.domain.model.UserTopic;
import com.zed.next.ui.common.DialogResult;
import com.zed.next.ui.common.EndlessRecyclerViewScrollListener;
import com.zed.next.ui.common.SwipeHelper;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class NextFragment extends Fragment {


    private NextViewModel nextViewModel;
    private TopicPageAdapter topicAdapter;
    private View view;
    private String message;

    private RecyclerView recyclerView;
    private ConstraintLayout constraintLayout;
    private List<UserTopic> al = new ArrayList<>();

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflate menu
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_home, container, false);
        constraintLayout = view.findViewById(R.id.nextLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        message = getActivity().getIntent().getStringExtra(MainActivity.CURRENT_USER);
        nextViewModel = ViewModelProviders.of(this).get(NextViewModel.class);

        initRecyclerView();
        enableSwipeToDeleteAndUndo();

        nextViewModel.getUserNextTopics(message).observe(this, new Observer<List<UserTopic>>() {
            @Override
            public void onChanged(List<UserTopic> userTopics) {
                al.addAll(userTopics);
                topicAdapter.notifyDataSetChanged();
            }
        });
        return view;
    }

    private void initRecyclerView() {

        topicAdapter = new TopicPageAdapter(al, getContext(), new NextAdapterListener() {
            @Override
            public void iconButtonOnClick(View v, UserTopic position) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                DoneDialogFragment.display(fm, new DialogResult<TopicDoneStat>() {
                            @Override
                            public void onSubmit(TopicDoneStat result) {
                                position.setTopicDoneStat(result);
                                nextViewModel.topicDone(message, position);
                            }
                            @Override
                            public void onCancel() {

                            }
                        });
            }

            @Override
            public void iconImageViewOnClick(View v, UserTopic topic) {

            }

            @Override
            public void onSwipeToDelete(UserTopic topic) {

            }
        });

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
        recyclerView.setAdapter(topicAdapter);
    }

    private void enableSwipeToDeleteAndUndo() {

        SwipeHelper swipeHelper = new SwipeHelper(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                final int position = viewHolder.getAdapterPosition();
                nextViewModel.topicDelete(message,al.get(position)).observe(getActivity(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        if(aBoolean)
                        {
                            al.remove(position);
                            topicAdapter.notifyDataSetChanged();
                            Snackbar.make(constraintLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG).show();
                        }
                        else
                            Snackbar.make(constraintLayout, "Failed to remove from the list", Snackbar.LENGTH_LONG).show();
                    }
                });
            }

        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeHelper);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

}
