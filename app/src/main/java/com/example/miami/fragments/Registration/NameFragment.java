package com.example.miami.fragments.Registration;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.miami.R;

public class NameFragment extends Fragment implements View.OnClickListener {


    public NameFragment() {
        super();
    }

    public static NameFragment newInstance() {
        return new NameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface OnClickNextButton {
        void onClicked();
    }

    private OnClickNextButton clicker;

    @Override
    public void onClick(View v) {
        clicker.onClicked();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name, container, false);
        view.findViewById(R.id.nextButton).setOnClickListener(this);
        return view;
    }
}