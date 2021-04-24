package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.miami.R;
import com.example.miami.fragments.Registration.NameFragment;
import com.example.miami.fragments.Registration.DateBirthFragment;
import com.example.miami.fragments.Registration.GenderPickerFragment;

import com.example.miami.fragments.Registration.HeaderRegistrationFragment;
import com.example.miami.fragments.Registration.IdentityFragment;


import android.app.ActionBar;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import org.w3c.dom.Text;


public class RegistrationActivity extends AppCompatActivity
        implements NameFragment.OnClickNextButtonListener, DateBirthFragment.OnClickNextButtonListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_registration, new HeaderRegistrationFragment(), null)
                .add(R.id.fragment_registration, new IdentityFragment(), null)
                .commit();
    }

    @Override
    public void onClicked() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.registration_view, new DateBirthFragment(), null)
                .commit();
    }

    @Override
    public void onClickedGender() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.registration_view, new GenderPickerFragment(), null)
                .commit();
    }
}