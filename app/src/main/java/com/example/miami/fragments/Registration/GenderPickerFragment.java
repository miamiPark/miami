package com.example.miami.fragments.Registration;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.miami.R;

public class GenderPickerFragment extends Fragment implements View.OnClickListener {

    private GenderPickerFragment.OnClickNextButtonListener mListener;

    public GenderPickerFragment() {
        super();
    }

    public interface OnClickNextButtonListener {
        void onClickedDetailUserInfo();
    }

    public static GenderPickerFragment newInstance() {
        return new GenderPickerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof GenderPickerFragment.OnClickNextButtonListener) {
            mListener = (GenderPickerFragment.OnClickNextButtonListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement NameFragment.OnClickNextButtonListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_gender_picker, container, false);
        view.findViewById(R.id.nextButton).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) { mListener.onClickedDetailUserInfo(); }
}