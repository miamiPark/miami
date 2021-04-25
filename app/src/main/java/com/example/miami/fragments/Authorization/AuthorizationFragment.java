package com.example.miami.fragments.Authorization;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;


import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.activities.FeedActivity;
import com.example.miami.models.authorization.LoginState;
import com.example.miami.viewModels.AuthorizationViewModel;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;



/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AuthorizationFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AuthorizationFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private AuthorizationViewModel authorizationViewModel;
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AuthorizationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AuthorizationFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AuthorizationFragment newInstance(String param1, String param2) {
        AuthorizationFragment fragment = new AuthorizationFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_authorization, container, false);
    }

    @SuppressWarnings("ConstantConditions")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        authorizationViewModel = new ViewModelProvider(getActivity()).get(AuthorizationViewModel.class);

        Button buttonLogin = view.findViewById(R.id.phone_next);

        authorizationViewModel.getProgress()
                .observe(getViewLifecycleOwner(), new LoginObserver(buttonLogin));


        EditText password = view.findViewById(R.id.password_input);
        final EditText phoneInput = (EditText) view.findViewById(R.id.phone_input);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                authorizationViewModel.login(phoneInput.getText().toString(), password.getText().toString());
            }
        });

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
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private class LoginObserver implements Observer<LoginState> {
        private Button loginButton;

        public LoginObserver(Button loginBtn) {
            this.loginButton = loginBtn;
        }

        @Override
        public void onChanged(LoginState loginState) {
            if (loginState == LoginState.FAILED) {
                loginButton.setEnabled(true);
                loginButton.setBackgroundColor(getResources().getColor(android.R.color.holo_red_dark));
                Toast.makeText(getContext(), "Неверные имя или пароль", Toast.LENGTH_LONG).show();
            } else if (loginState == LoginState.ERROR) {
                loginButton.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_light));
                loginButton.setEnabled(true);
                Toast.makeText(getContext(), "Заполните все поля", Toast.LENGTH_LONG).show();
            } else if (loginState == LoginState.IN_PROGRESS) {
                loginButton.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
                loginButton.setEnabled(false);
            } else if (loginState == LoginState.SUCCESS) {
                Toast.makeText(getContext(), "Success login", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), FeedActivity.class);
                startActivity(intent);
            } else {
                loginButton.setBackground(getContext().getDrawable(android.R.drawable.btn_default));
                loginButton.setEnabled(true);
            }
        }

    }

}