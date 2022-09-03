package com.example.navigationdrawer.ui.other;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.navigationdrawer.R;
import com.example.navigationdrawer.ui.other.OtherViewModel;


public class OtherFragment extends Fragment {

    private OtherViewModel otherViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        otherViewModel =
                new ViewModelProvider(this).get(OtherViewModel.class);
        View root = inflater.inflate(R.layout.fragment_other, container, false);
        final TextView textView = root.findViewById(R.id.text_other);
        otherViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

}