package com.example.miami.fragments.Registration;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.miami.R;
import com.example.miami.models.registration.RegistrationState;
import com.example.miami.viewModels.RegistrationViewModel;
import com.skydoves.powerspinner.OnSpinnerItemSelectedListener;
import com.skydoves.powerspinner.PowerSpinnerView;

public class DetailUserInfoFragment extends Fragment {

    private RegistrationViewModel mRegistrationViewModel;

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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRegistrationViewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);

        Button buttonNext = view.findViewById(R.id.detail_info_next);

        mRegistrationViewModel.getProgress()
                .observe(getViewLifecycleOwner(), new DetailUserInfoObserver(buttonNext));

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText job = view.findViewById(R.id.job_input);
                PowerSpinnerView university = view.findViewById(R.id.university_input);
                EditText aboutMe = view.findViewById(R.id.about_me_input);

                mRegistrationViewModel.detailInfo(
                        job.getText().toString(),
                        university.getText().toString(),
                        aboutMe.getText().toString()
                );
            }
        });
    }

    public class DetailUserInfoObserver implements Observer<RegistrationState> {

        private final Button mNextButton;

        public DetailUserInfoObserver(Button nextButton) {
            mNextButton = nextButton;
        }

        @Override
        public void onChanged(RegistrationState registrationState) {
            if (registrationState == RegistrationState.DETAIL_INFO_SUCCESS) {
                mNextButton.setEnabled(true);
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.registration_view, new PhotoFragment())
                        .commit();
            }
        }
    }
}