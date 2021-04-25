package com.example.miami.fragments.Registration;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.DatePickerDialog;
import android.widget.DatePicker;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.example.miami.R;

public class DateBirthFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener, View.OnClickListener {

    public DateBirthFragment() {
        super();
    }

    public static DateBirthFragment newInstance() {
        return new DateBirthFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public interface OnClickNextButtonListener {
        void onClickedGender();
    }

    private DateBirthFragment.OnClickNextButtonListener mListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        if (context instanceof DateBirthFragment.OnClickNextButtonListener) {
            mListener = (DateBirthFragment.OnClickNextButtonListener) context;
        } else {
            throw new ClassCastException(context.toString()
                    + " must implement DateBirthFragment.OnClickNextButtonListener");
        }
    }

    @Override
    public void onClick(View v) {
        mListener.onClickedGender();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_date_birth, container, false);
        view.findViewById(R.id.date_next).setOnClickListener(this);
        return view;

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = sdf.format(c.getTime());
    }
}