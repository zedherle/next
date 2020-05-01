package com.zed.next.ui.discover;

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
import com.zed.next.data.datasource.remote.MoviesModel;
import com.zed.next.data.datasource.services.api.APIClient;
import com.zed.next.data.datasource.services.api.APIService;
import com.zed.next.data.datasource.services.api.MovieResponse;
import com.zed.next.domain.model.UserTopic;
import com.zed.next.ui.common.EndlessRecyclerViewScrollListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;




public class DiscoverFragment extends Fragment {

    private RecyclerView recyclerView;
    private Call<MovieResponse> call;
    private DiscoverViewModel discoverViewModel;
    private SearchPageAdapter searchPageAdapter;
    private View view;
    private List<MoviesModel> al = new ArrayList<>();
    private String message;


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflate menu


        inflater.inflate(R.menu.menu_search, menu);
        SearchView searchView= (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.onActionViewExpanded();
        searchView.clearFocus();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // adapter.getFilter().filter(newText);

                discoverViewModel.getMoviesByQuery(newText).observe(getActivity(), moviesModels -> {
                    al.clear();
                    al.addAll(moviesModels);
                    searchPageAdapter.notifyDataSetChanged();
                });
                return true;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //handle menu item clicks
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_discover, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);


        Intent intent = getActivity().getIntent();
        message = intent.getStringExtra(MainActivity.CURRENT_USER);


        discoverViewModel = ViewModelProviders.of(this).get(DiscoverViewModel.class);

        discoverViewModel.init();

        initRecyclerViewForSearchResults();

        discoverViewModel.getNextTopics().observe(this, new Observer<List<MoviesModel>>() {
            @Override
            public void onChanged(List<MoviesModel> moviesModels) {

                al.addAll(moviesModels);
                searchPageAdapter.notifyDataSetChanged();

            }
        });

        return view;

    }

    private void initRecyclerViewForSearchResults() {

        searchPageAdapter  = new SearchPageAdapter(al, getContext(), new SearchAdapterListener() {
            @Override
            public void iconButtonOnClick(View v, UserTopic position) {

                discoverViewModel.topicNext(message, position);
            }

            @Override
            public void iconImageViewOnClick(View v, UserTopic topic) {

            }

        });
          recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
     //   recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
     //   recyclerView.setLayoutManager(new StaggeredGridLayoutManager());
        EndlessRecyclerViewScrollListener scrollListener = new EndlessRecyclerViewScrollListener(new LinearLayoutManager(getContext())) {
            @Override
            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
                if ((page + 1) <= 2) {
                    loadPage(page + 1);
                }
            }
        };
        recyclerView.addOnScrollListener(scrollListener);
        recyclerView.setAdapter(searchPageAdapter);
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
