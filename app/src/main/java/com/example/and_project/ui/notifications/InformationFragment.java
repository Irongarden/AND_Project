package com.example.and_project.ui.notifications;

import android.content.Intent;
import android.net.Uri;
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

import com.example.and_project.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class InformationFragment extends Fragment {

    private InformationViewModel informationViewModel;
    FloatingActionButton actionButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        informationViewModel =
                new ViewModelProvider(this).get(InformationViewModel.class);
        View root = inflater.inflate(R.layout.fragment_information, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);
        actionButton = root.findViewById(R.id.informationFloting);
        actionButton.setOnClickListener(this::onclick);
        informationViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void onclick(View view) {
        Uri uri = Uri.parse("https://pokemon.fandom.com/wiki/Pok%C3%A9mon_Wiki");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
}