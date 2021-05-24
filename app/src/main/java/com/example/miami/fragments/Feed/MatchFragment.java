package com.example.miami.fragments.Feed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.models.feed.UserFeed;

import java.util.ArrayList;
import java.util.List;

public class MatchFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public MatchFragment() {
        super();
    }

    public static MatchFragment newInstance(String param1, String param2) {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_match, container, false);
    }

    private class MatchList {
        public View view;
        public ArrayList<UserFeed> userFeeds;
        private Observer<List<UserFeed>> observer;

        public void setView(View view) {
            this.view = view;
        }

        public MatchList() {
            userFeeds = new ArrayList<UserFeed>();
            observer =  new Observer<List<UserFeed>>() {
                @Override
                public void onChanged(List<UserFeed> users) {
                    if (!users.isEmpty()) {
                        userFeeds = (ArrayList<UserFeed>) users;
                        if (userFeeds.get(userFeeds.size() - 1).id == -1) {
                            Toast.makeText(getContext(), "Ошибка при загрузке данных, попробуйте позже", Toast.LENGTH_LONG).show();
                        } else {
                            UserFeed user = userFeeds.get(1);
                            TextView name = view.findViewById(R.id.match_name);
                            String res = user.name;
                            name.setText(res);
                        }
                    }
                }
            };
        }
    }
}