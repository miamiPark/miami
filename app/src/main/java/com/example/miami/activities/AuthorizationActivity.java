package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.miami.R;
import com.example.miami.fragments.Authorization.AuthorizationFragment;
import com.example.miami.fragments.Registration.HeaderRegistrationFragment;

public class AuthorizationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorization);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_view, new HeaderRegistrationFragment(), null)
                .add(R.id.fragment_view, new AuthorizationFragment(), null)
                .commit();
    }
}