package com.example.miami.fragments.Registration;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.models.registration.RegistrationState;
import com.example.miami.viewModels.RegistrationViewModel;

import info.hoang8f.android.segmented.SegmentedGroup;

public class GenderPickerFragment extends Fragment {

    private RegistrationViewModel mRegistrationViewModel;

    public GenderPickerFragment() {
        super();
    }

    public static GenderPickerFragment newInstance() {
        return new GenderPickerFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gender_picker, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRegistrationViewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);

        Button buttonNext = view.findViewById(R.id.gender_next);
        buttonNext.setEnabled(false);

        mRegistrationViewModel.getProgress()
                .observe(getViewLifecycleOwner(), new GenderPickerObserver(buttonNext));

        SegmentedGroup group = view.findViewById(R.id.segmented2);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                buttonNext.setEnabled(true);
            }
        });

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = group.getCheckedRadioButtonId();
                RadioButton radio = view.findViewById(id);

                String sex = group.indexOfChild(radio) == 0 ? "female" : "male";
                mRegistrationViewModel.gender(sex);
            }
        });
    }

    public class GenderPickerObserver implements Observer<RegistrationState> {

        private final Button mNextButton;

        public GenderPickerObserver(Button nextButton) {
            mNextButton = nextButton;
        }

        @Override
        public void onChanged(RegistrationState registrationState) {
            if (registrationState == RegistrationState.GENDER_SUCCESS) {
                mNextButton.setEnabled(true);
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.registration_view, new DetailUserInfoFragment())
                        .commit();
            }
        }
    }
}