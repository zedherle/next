package com.zed.next.ui.neXt;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.zed.next.R;
import com.zed.next.domain.model.TopicDoneStat;
import com.zed.next.domain.model.TopicMedium;
import com.zed.next.ui.common.DateProvider;
import com.zed.next.ui.common.DialogResult;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

public class DoneDialogFragment extends DialogFragment {


    public static final String TAG = "example_dialog";
    private AutoCompleteTextView etMedium, etRank;
    private AutoCompleteTextView etWatchedOn;
    private DoneDialogViewModel doneDialogViewModel;
    private Toolbar toolbar;
    private Button btSaveDoneStat;
    private TopicMedium medium;
    private DialogResult result;

    public static DoneDialogFragment display(FragmentManager fragmentManager, DialogResult result) {
        DoneDialogFragment doneDialogFragment = new DoneDialogFragment(result);
        doneDialogFragment.show(fragmentManager, TAG);

        return doneDialogFragment;
    }

    private DoneDialogFragment(DialogResult result) {
        this.result = result;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setHasOptionsMenu(true);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_FullScreenDialog);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.done_tag_dialog,container,false);
        toolbar = view.findViewById(R.id.toolbar);
        etMedium = view.findViewById(R.id.etMedium);
        etRank = view.findViewById(R.id.etRank);
        btSaveDoneStat = view.findViewById(R.id.btSaveDoneStat);

        doneDialogViewModel = ViewModelProviders.of(this).get(DoneDialogViewModel.class);


        doneDialogViewModel.getAllMedium("MOVIE").observe(this, new Observer<List<TopicMedium>>() {
            @Override
            public void onChanged(List<TopicMedium> topicMedia) {
                ArrayList<String> ar = new ArrayList<String>();
                for (TopicMedium medium: topicMedia)
                {
                        ar.add(medium.getName());
                }
                ArrayAdapter<String> mediumAdapter =
                        new ArrayAdapter<>(
                                getContext(),
                                R.layout.dropdown_menu_pop_up,
                                ar);
                etMedium.setAdapter(mediumAdapter);
            }
        });

        btSaveDoneStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TopicDoneStat topicDoneStat = new TopicDoneStat();
                topicDoneStat.setMedium(etMedium.getText().toString());
                topicDoneStat.setVote(Integer.parseInt(etRank.getText().toString()));
                topicDoneStat.setCreated_on(DateProvider.getNowDate());
                topicDoneStat.setFavourite(false);
                result.onSubmit(topicDoneStat);
                dismiss();
            }
        });

    //    String[] MEDIUM = new String[] {"NetFlix", "Prime", "Hotstar", "ZeeCinema"};


        String [] vote = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        ArrayAdapter<String> voteAdapter =
                new ArrayAdapter<>(
                        getContext(),
                        R.layout.dropdown_menu_pop_up,
                        vote);

        etRank.setAdapter(voteAdapter);
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onStart() {
        super.onStart();

        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setWindowAnimations(R.style.AppTheme_Slide);
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        toolbar.setNavigationOnClickListener(v -> dismiss());
        toolbar.setTitle("Done Already ?");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.inflateMenu(R.menu.menu_dialog);
        toolbar.setOnMenuItemClickListener(item -> {
            dismiss();
            return true;
        });
    }
}
