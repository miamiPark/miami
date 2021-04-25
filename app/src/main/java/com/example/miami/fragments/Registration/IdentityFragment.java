package com.example.miami.fragments.Registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.models.registration.RegistrationState;
import com.example.miami.viewModels.RegistrationViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IdentityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdentityFragment extends Fragment {

    private RegistrationViewModel mRegistrationViewModel;

    public IdentityFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment IdentityFragment.
     */
    public static IdentityFragment newInstance() {
        IdentityFragment fragment = new IdentityFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_identity, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRegistrationViewModel = new ViewModelProvider(getActivity()).get(RegistrationViewModel.class);

        Button buttonNext = view.findViewById(R.id.phone_next);

        mRegistrationViewModel.getProgress()
                .observe(getViewLifecycleOwner(), new IdentityObserver(buttonNext));

        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText phoneInput = (EditText) view.findViewById(R.id.phone_input);
                EditText password = (EditText) view.findViewById(R.id.password_input);
                EditText repeatPassword = (EditText) view.findViewById(R.id.repeat_password_input);

                mRegistrationViewModel.identity(
                        phoneInput.getText().toString(),
                        password.getText().toString(),
                        repeatPassword.getText().toString()
                );
            }
        });

        final EditText phoneInput = (EditText) view.findViewById(R.id.phone_input);
        phoneInput.addTextChangedListener(new TextWatcher() {
            private String lastChar = "";

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int digits = phoneInput.getText().toString().length();
                if (digits > 1) {
                    lastChar = phoneInput.getText().toString().substring(digits - 1);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int digits = phoneInput.getText().toString().length();
                if (!lastChar.equals("-")) {
                    if (digits == 3 || digits == 7) {
                        phoneInput.append("-");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    public class IdentityObserver implements Observer<RegistrationState> {

        public Button mNextButton;

        public IdentityObserver(Button nextButton) {
            mNextButton = nextButton;
        }

        @Override
        public void onChanged(RegistrationState registrationState) {
            if (registrationState == RegistrationState.ERROR) {
                mNextButton.setEnabled(true);
                Toast.makeText(getContext(), "Пароли не совпадают", Toast.LENGTH_LONG).show();
            } else if (registrationState == RegistrationState.MIDDLEWARE_SUCCESS) {
                mNextButton.setEnabled(true);
                getActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.registration_view, new NameFragment())
                        .commit();
            }
        }
    }
}