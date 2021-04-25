package com.example.miami.fragments.Registration;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.miami.R;

public class DetailUserInfoFragment extends Fragment {

    public DetailUserInfoFragment() {
        super();
    }

    public static DetailUserInfoFragment newInstance() {
        return new DetailUserInfoFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed_user_info, container, false);
    }
}