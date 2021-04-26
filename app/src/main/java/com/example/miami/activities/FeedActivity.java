package com.example.miami.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.miami.ApplicationModified;
import com.example.miami.R;
import com.example.miami.fragments.Feed.CardFragment;
import com.example.miami.fragments.Feed.HeaderFragment;
import com.example.miami.viewModels.FeedViewModel;

public class FeedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_view, new HeaderFragment(), null)
                .commit();

        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_view, new CardFragment(), null)
                .commit();

    }

    @Override
    public void onBackPressed() {
        // do nothing because you don't want them to leave when it's pressed
    }
}