package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.miami.R;
import com.example.miami.fragments.Registration.NameFragment;
import com.example.miami.fragments.Registration.DateBirthFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegistrationActivity extends AppCompatActivity implements NameFragment.OnClickNextButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.registration_view, new NameFragment(), null)
                .commit();
    }

    @Override
    public void onClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.registration_view, new DateBirthFragment(), null)
                .commit();
    }
}