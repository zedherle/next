//package com.zed.next.ui.search;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.Menu;
//import android.view.MenuInflater;
//import android.view.MenuItem;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.inputmethod.EditorInfo;
//
//import com.zed.next.R;
//import com.zed.next.ui.main.Movies;
//
//import java.util.ArrayList;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.widget.SearchView;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class SearchFragment extends Fragment {
//
//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//
//     //   inflater.inflate(R.menu.search_menu, menu);
//     //   MenuItem searchItem = menu.findItem(R.id.actionSearch);
//        SearchView searchView = (SearchView) searchItem.getActionView();
//        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        return super.onOptionsItemSelected(item);
//    }
//    //    @Override
////    public void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setHasOptionsMenu(true);
////    }
//
//    private RecyclerView recyclerView;
//    private SearchView searchView;
//    private SearchViewAdapter adapter;
//    private ArrayList<Movies> planetArrayList;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view;
//
//        view = inflater.inflate(R.layout.search_layout, container, false);
//
//        searchView = (SearchView)view.findViewById(R.id.searchView) ;
//        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//
//        initView();
//
//        return view;
//
//    }
//
//    private void initView() {
//
//        planetArrayList = new ArrayList<>();
//        createListData();
//        adapter = new SearchViewAdapter(getContext(),planetArrayList);
//        recyclerView.setAdapter(adapter);
//
//
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String newText) {
//                adapter.getFilter().filter(newText);
//                return false;
//            }
//        });
//    }
//
//    private void createListData() {
//        Movies planet = new Movies("Earth", 150, 10, 12750);
//        planetArrayList.add(planet);
//        planet = new Movies("Jupiter", 778, 26, 143000);
//        planetArrayList.add(planet);
//        planet = new Movies("Mars", 228, 4, 6800);
//        planetArrayList.add(planet);
//        planet = new Movies("Pluto", 5900, 1, 2320);
//        planetArrayList.add(planet);
//        planet = new Movies("Venus", 108, 9, 12750);
//        planetArrayList.add(planet);
//        planet = new Movies("Saturn", 1429, 11, 120000);
//        planetArrayList.add(planet);
//        planet = new Movies("Mercury", 58, 4, 4900);
//        planetArrayList.add(planet);
//        planet = new Movies("Neptune", 4500, 12, 50500);
//        planetArrayList.add(planet);
//        planet = new Movies("Uranus", 2870, 9, 52400);
//        planetArrayList.add(planet);
//       // adapter.notifyDataSetChanged();
//    }
//}
