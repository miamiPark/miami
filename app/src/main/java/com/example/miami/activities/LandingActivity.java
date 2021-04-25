package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miami.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // sign up button redirect
        findViewById(R.id.button_sign_up)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LandingActivity.this, RegistrationActivity.class));
                    }
                });

        // sign in button redirect
        findViewById(R.id.button_sign_in)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // add auth activity
                        startActivity(new Intent(LandingActivity.this, AuthorizationActivity.class));
                    }
                });
    }
}