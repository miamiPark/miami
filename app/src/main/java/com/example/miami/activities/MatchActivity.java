package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;
import com.example.miami.R;
import com.example.miami.fragments.Match.MatchFragment;
import com.example.miami.fragments.Registration.HeaderRegistrationFragment;

import android.os.Bundle;

public class MatchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_registration, new HeaderRegistrationFragment(), null)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_match, new MatchFragment(), null)
                .commit();
    }
}