package com.example.miami.fragments.Registration;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.app.DatePickerDialog;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.miami.R;
import com.example.miami.models.registration.RegistrationState;
import com.example.miami.viewModels.RegistrationViewModel;

import org.jetbrains.annotations.NotNull;

public class DateBirthFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private RegistrationViewModel mRegistrationViewModel;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_date_birth, container, false);
    }

    @NotNull
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

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String formattedDate = sdf.format(c.getTime());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRegistrationViewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);

        Button buttonNext = view.findViewById(R.id.date_next);

        mRegistrationViewModel.getProgress()
                .observe(getViewLifecycleOwner(), new DateBirthObserver(buttonNext));

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePicker date = view.findViewById(R.id.datePicker);
                int day = date.getDayOfMonth();
                int month = date.getMonth();
                int year = date.getYear();

                mRegistrationViewModel.date(day, month, year);
            }
        });
    }

    public class DateBirthObserver implements Observer<RegistrationState> {

        private final Button mNextButton;

        public DateBirthObserver(Button nextButton) {
            mNextButton = nextButton;
        }

        @Override
        public void onChanged(RegistrationState registrationState) {
            if (registrationState == RegistrationState.ERROR) {
                mNextButton.setEnabled(true);
                Toast.makeText(getContext(), "Неактуальная дата", Toast.LENGTH_SHORT).show();
            } else if (registrationState == RegistrationState.DATE_SUCCESS) {
                mNextButton.setEnabled(true);
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.registration_view, new GenderPickerFragment())
                        .commit();
            }
        }
    }
}