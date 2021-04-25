package com.example.miami.fragments.Registration;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.miami.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link IdentityFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IdentityFragment extends Fragment {

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
        // args.putString(ARG_PARAM1, param1);
        // args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // if (getArguments() != null) {
            // mParam1 = getArguments().getString(ARG_PARAM1);
            // mParam2 = getArguments().getString(ARG_PARAM2);
        // }
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
}