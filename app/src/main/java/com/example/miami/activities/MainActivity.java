package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.miami.R;
import com.example.miami.fragments.Registration.NameFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_name);
        setContentView(R.layout.fragment_date_birth);
    }
}