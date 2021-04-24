package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;

import com.example.miami.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        // sign up button redirect
        findViewById(R.id.button_sing_up)
                .setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(LandingActivity.this, RegistrationActivity.class));
                    }
                });
    }
}