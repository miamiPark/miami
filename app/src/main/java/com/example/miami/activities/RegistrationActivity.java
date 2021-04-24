package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.miami.R;
import com.example.miami.fragments.Registration.HeaderRegistrationFragment;
import com.example.miami.fragments.Registration.IdentityFragment;

import android.app.ActionBar;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import org.w3c.dom.Text;

public class RegistrationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_registration, new HeaderRegistrationFragment(), null)
                .add(R.id.fragment_registration, new IdentityFragment(), null)
                .commit();
    }
}