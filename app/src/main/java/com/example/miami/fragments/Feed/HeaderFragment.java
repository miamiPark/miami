package com.example.miami.fragments.Feed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.miami.R;
import com.example.miami.viewModels.FeedViewModel;

import java.util.Objects;
import com.example.miami.fragments.Profile.ProfileFragment;
import com.example.miami.fragments.Registration.DateBirthFragment;

public class HeaderFragment extends Fragment {
    public HeaderFragment() {
        super();
    }

    public static HeaderFragment newInstance(String param1, String param2) {
        HeaderFragment fragment = new HeaderFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_header, container, false);
        ImageButton match = view.findViewById(R.id.headerMatch);

        match.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("users", "CLICK!!!");
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_feed, new MatchFragment())
                        .commit();
            }
        });
        View profile = view.findViewById(R.id.headerProfile);
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("авваъъавъваъавъваъа", "я здесь");
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_feed, new ProfileFragment())
                        .commit();
                Log.w("авваъъавъваъавъваъа", "и здесь");
            }
        });

        View feed = view.findViewById(R.id.headerFeed);
        feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.w("авваъъавъваъавъваъа", "я здесь");
                requireActivity()
                        .getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_feed, new CardFragment())
                        .commit();
                Log.w("авваъъавъваъавъваъа", "и здесь");
            }
        });
        return view;
    }
}