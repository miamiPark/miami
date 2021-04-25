package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.miami.R;

import com.example.miami.fragments.Registration.NameFragment;
import com.example.miami.fragments.Registration.DateBirthFragment;
import com.example.miami.fragments.Registration.GenderPickerFragment;
import com.example.miami.fragments.Registration.DetailUserInfoFragment;
import com.example.miami.fragments.Registration.PhotoFragment;

import com.example.miami.fragments.Registration.HeaderRegistrationFragment;
import com.example.miami.fragments.Registration.IdentityFragment;

import android.os.Bundle;


import org.w3c.dom.Text;


public class RegistrationActivity extends AppCompatActivity implements DateBirthFragment.OnClickNextButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_registration, new HeaderRegistrationFragment(), null)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.registration_view, new IdentityFragment(), null)
                .commit();
    }

    @Override
    public void onClickedGender() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_registration, new GenderPickerFragment(), null)
                .add(R.id.fragment_registration, new HeaderRegistrationFragment(), null)
                .commit();
    }
}