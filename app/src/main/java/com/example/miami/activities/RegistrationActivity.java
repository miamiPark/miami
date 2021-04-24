package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miami.R;
import com.example.miami.fragments.Registration.HeaderRegistrationFragment;

import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_view, new HeaderRegistrationFragment(), null)
                .commit();

    }
}