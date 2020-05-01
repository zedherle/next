package com.zed.next.ui.profile;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.zed.next.MainActivity;
import com.zed.next.R;
import com.zed.next.ui.login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    private View view;
    private String message;
    private TextView txtProfileName;
    private Button btSignout;

    private FirebaseAuth auth;

    private GoogleSignInOptions gso;
    private GoogleSignInClient mGoogleSignInClient;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        auth = FirebaseAuth.getInstance();

        txtProfileName = view.findViewById(R.id.profile_name);
       // btSignout = view.findViewById(R.id.btSignout);

//        btSignout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                signOut();
//            }
//        });

        Intent intent = getActivity().getIntent();
        message = intent.getStringExtra(MainActivity.CURRENT_USER);

        profileViewModel = ViewModelProviders.of(this).get(ProfileViewModel.class);

        txtProfileName.setText(message);

        return view;
    }

    private void signOut() {
        // Firebase sign out
        auth.signOut();

        // Google sign out
        LoginActivity.mGoogleSignInClient.signOut().addOnCompleteListener(getActivity(),
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        getActivity().finish();
                    }
                });
    }

}
