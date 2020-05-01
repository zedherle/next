package com.zed.next.ui.neXt;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.zed.next.MainActivity;
import com.zed.next.R;
import com.zed.next.data.datasource.services.api.APIClient;
import com.zed.next.data.datasource.services.api.APIService;
import com.zed.next.data.datasource.services.api.MovieResponse;
import com.zed.next.domain.model.UserTopic;
import com.zed.next.ui.common.EndlessRecyclerViewScrollListener;
import com.zed.next.ui.common.SwipeHelper;
import com.zed.next.ui.discover.SearchPageAdapter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;


public class NextFragment extends Fragment {

    private RecyclerView recyclerView;
    private Call<MovieResponse> call;
    private NextViewModel nextViewModel;
    private TopicPageAdapter movieAdapter;
    private SearchPageAdapter searchPageAdapter;
    private View view;
    private List<UserTopic> al = new ArrayList<>();
    String message;


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
        recyclerView = view.findViewById(R.id.recyclerView);

        Intent intent = getActivity().getIntent();
        message = intent.getStringExtra(MainActivity.CURRENT_USER);

        nextViewModel = ViewModelProviders.of(this).get(NextViewModel.class);

        initRecyclerView();

        enableSwipeToDeleteAndUndo();

        nextViewModel.getUserNextTopics(message).observe(this, new Observer<List<UserTopic>>() {
            @Override
            public void onChanged(List<UserTopic> userTopics) {
                al.addAll(userTopics);
                movieAdapter.notifyDataSetChanged();
            }
        });


        return view;


    }

    private void initRecyclerView() {

        movieAdapter = new TopicPageAdapter(al, getContext(), new NextAdapterListener() {
            @Override
            public void iconButtonOnClick(View v, UserTopic position) {
                    nextViewModel.topicDone(message, position);
            }

            @Override
            public void iconImageViewOnClick(View v, UserTopic topic) {

            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(new LinearLayoutManager(getContext())) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if ((page + 1) <= 2) {
                    loadPage(page + 1);
                }
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.setAdapter(movieAdapter);


    }

    private void enableSwipeToDeleteAndUndo() {
        SwipeHelper swipeToDeleteCallback = new SwipeHelper(getContext()) {
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                final int position = viewHolder.getAdapterPosition();
//                final String item = mAdapter.getData().get(position);
//
//                mAdapter.removeItem(position);

//                Snackbar snackbar = Snackbar
//                        .make(coordinatorLayout, "Item was removed from the list.", Snackbar.LENGTH_LONG);
//                snackbar.setAction("UNDO", new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//
//                        mAdapter.restoreItem(item, position);
//                        recyclerView.scrollToPosition(position);
//                    }
//                });

   //             snackbar.setActionTextColor(Color.YELLOW);
     //           snackbar.show();

            }
        };

        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchhelper.attachToRecyclerView(recyclerView);
    }

    private void loadPage(final int page) {

        APIService movieDataService = APIClient.getClient();
        switch (page) {
            case 1:
                call = movieDataService.getMovies(page, "df0009b053c2106b9e28a06fab0daedf");
                break;
            case 2:
                call = movieDataService.getTopRatedMovies(page, "df0009b053c2106b9e28a06fab0daedf");
                break;
        }

    }

}
