package com.example.miami.fragments.Registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miami.R;

public class NameFragment extends Fragment {

    private static final String NAME = "name";

    private String name;

    public NameFragment() {
        super();
    }

    public static NameFragment newInstance(String param1) {
        NameFragment fragment = new NameFragment();
        Bundle args = new Bundle();
        args.putString(NAME, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            name = getArguments().getString(NAME);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_name, container, false);
    }
}