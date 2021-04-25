package com.example.miami.fragments.Registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.models.registration.RegistrationState;
import com.example.miami.viewModels.RegistrationViewModel;

import java.util.Objects;

public class NameFragment extends Fragment {

    private RegistrationViewModel mRegistrationViewModel;

    public NameFragment() {}

    public static NameFragment newInstance() {
        return new NameFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_name, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRegistrationViewModel = new ViewModelProvider(Objects.requireNonNull(getActivity())).get(RegistrationViewModel.class);

        Button buttonNext = view.findViewById(R.id.name_next);

        mRegistrationViewModel.getProgress()
                .observe(getViewLifecycleOwner(), new NameObserver(buttonNext));

        buttonNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                EditText nameInput = view.findViewById(R.id.name_input);

                mRegistrationViewModel.name(nameInput.getText().toString());
            }
        });
    }

    public class NameObserver implements Observer<RegistrationState> {

        private final Button mNextButton;

        public NameObserver(Button nextButton) {
            mNextButton = nextButton;
        }

        @Override
        public void onChanged(RegistrationState registrationState) {
            if (registrationState == RegistrationState.ERROR) {
                mNextButton.setEnabled(true);
                Toast.makeText(getContext(), "Заполните поле", Toast.LENGTH_LONG).show();
            } else if (registrationState == RegistrationState.NAME_SUCCESS) {
                mNextButton.setEnabled(true);
                Objects.requireNonNull(getActivity())
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.registration_view, new DateBirthFragment())
                        .commit();
            }
        }
    }
}