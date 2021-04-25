package com.example.miami.fragments.Registration;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.miami.R;

public class DetailUserInfoFragment extends Fragment implements View.OnClickListener {

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

    public interface OnClickNextButtonListener {
        void onClicked();
    }

    private OnClickNextButtonListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof OnClickNextButtonListener) {
            mListener = (OnClickNextButtonListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement NameFragment.OnClickNextButtonListener");
        }
    }

    @Override
    public void onClick(View v) {
        mListener.onClicked();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_name, container, false);
        view.findViewById(R.id.nextButton).setOnClickListener(this);
        return view;
    }
}