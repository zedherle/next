package com.zed.next.ui.profile;

import android.content.Intent;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.zed.next.MainActivity;
import com.zed.next.R;
import com.zed.next.domain.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private View view;
    private String message;
    private TextView txtProfileName;
    private ImageView ivProfile;
    private FirebaseAuth auth;
    private RecyclerView userListView = null;
    private SearchAdapter searchAdapter;
    private List<UserModel> al = null;
    Cursor c;
    SearchCursorAdapter searchCAdapter;
    public TextView txtTitle, txtRating, txtReleaseDate, txtTopicType;
    public Button btNext;
    public ImageView ivMovie;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        userListView = view.findViewById(R.id.userListView);

        auth = FirebaseAuth.getInstance();

        al = new ArrayList<UserModel>();

        txtProfileName = view.findViewById(R.id.profile_name);
        ivProfile = view.findViewById(R.id.ivProfile);

        initRecyclerViewForSearchResults();

        Intent intent = getActivity().getIntent();

        message = intent.getStringExtra(MainActivity.CURRENT_USER);

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        txtProfileName.setText(auth.getCurrentUser().getDisplayName());
        Glide.with(getActivity()).load(auth.getCurrentUser().getPhotoUrl()).into(ivProfile);

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //inflate menu

        inflater.inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.onActionViewExpanded();
        searchView.clearFocus();
        searchAdapter = new SearchAdapter(getActivity(), R.layout.movie_result_card, al);

        SearchView.SearchAutoComplete searchAutoComplete = (SearchView.SearchAutoComplete) searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        searchAutoComplete.setAdapter(searchAdapter);                                                                                                         searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();

                profileViewModel.getUsersByQuery(query).observe(getActivity(), new Observer<List<UserModel>>() {
                    @Override
                    public void onChanged(List<UserModel> userModels) {
                        al.clear();
                        al.addAll(userModels);






//                        c = getCursorFromList(al);
//                        searchCAdapter = new SearchCursorAdapter(getContext(), c, false);
//                        searchView.setSuggestionsAdapter(new CursorAdapter(getContext(), c, true) {
//
//                            @Override
//                            public View newView(Context context, Cursor cursor, ViewGroup parent) {
//                                return LayoutInflater.from(context).inflate(R.layout.movie_result_card, parent, false);
//                            }
//
//                            @Override
//                            public void bindView(View itemView, Context context, Cursor cursor) {
//                                txtTitle = itemView.findViewById(R.id.txtTitle);
//                                txtRating = itemView.findViewById(R.id.txtRating);
//                                txtReleaseDate = itemView.findViewById(R.id.txtReleaseDate);
//                                ivMovie = itemView.findViewById(R.id.ivMovie);
//
//                                txtTitle.setText(cursor.getColumnIndex("fname"));
//                                txtRating.setText(cursor.getColumnIndex("lname"));
//                            }
//                        });
//                        searchAdapter.notifyDataSetChanged();
                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // adapter.getFilter().filter(newText);
                 profileViewModel.getUsersByQuery(newText).observe(getActivity(), new Observer<List<UserModel>>() {

                     @Override
                     public void onChanged(List<UserModel> userModels) {
                              al.clear();
                              al.addAll(userModels);
                              searchAdapter.notifyDataSetChanged();
                     }
                 }          );

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

    private void initRecyclerViewForSearchResults() {


    }

    public Cursor getCursorFromList(List<UserModel> people) {
        MatrixCursor cursor = new MatrixCursor(
                new String[]{"_id", "fname", "lname", "image", "phone"}
        );

        for (UserModel person : people) {
            cursor.newRow().add("_id", Math.random())
                    .add("fname", person.getFname())
                    .add("lname", person.getLname())
                    .add("image", person.getImgPath())
                    .add("phone", person.getPhone());
        }

        return cursor;
    }


}
