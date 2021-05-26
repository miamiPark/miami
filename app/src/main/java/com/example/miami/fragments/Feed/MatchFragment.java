package com.example.miami.fragments.Feed;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.service.autofill.FieldClassification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.models.feed.UserFeed;
import com.example.miami.network.MatchRequestApi;
import com.example.miami.repository.MatchRepo;
import com.example.miami.viewModels.FeedViewModel;
import com.squareup.picasso.Picasso;

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

    private  Observer<MatchRequestApi.MatchBody> observer;
    private ArrayList<MatchRequestApi.ChatData> Chats;
    private FeedViewModel feedViewModel;
    private static Context MyContext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("МАТЧИ", "CREATE VIEW");
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        TextView textView = view.findViewById(R.id.match_name);

//        feedViewModel = new ViewModelProvider(getActivity())
//                .get(FeedViewModel.class);
//        feedViewModel.getMatch().observe(getViewLifecycleOwner(), observer);
//
//        MatchRepo matchRepo = MatchRepo.getInstance(MyContext);
//        LiveData<MatchRequestApi.MatchBody> matchBody = matchRepo.getMatch();
        textView.setText("Anna");

        observer = new Observer<MatchRequestApi.MatchBody>() {
            @Override
            public void onChanged(MatchRequestApi.MatchBody chatData) {
                Log.w("МАТЧИ", "Observer");
                if (chatData == null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_view, new HeaderFragment(), null)
                            .add(R.id.fragment_view, new NoMatchFragment(), null)
                            .commit();
                    return;
                }
//                if (!chatData[0].isEmpty()) {
//                    Log.w("МАТЧИ", chatData.toString());
//                    draw(view);
//                } else {
//                    Log.w("МАТЧИ", "ПУСТОЙ");
//                }
            }
        };

        return view;
    }

    public void draw(View view) {
        Log.w("draw", "РИСУЮ");
        MatchRequestApi.ChatData chat = Chats.get(1);
        TextView name = view.findViewById(R.id.match_item);
        String res = chat.partner.name + ", " + chat.messages[0];
        name.setText(res);
    }

    private class MatchList {
        public View view;
        public ArrayList<MatchRequestApi.ChatData> matchBody;
        private Observer<List<MatchRequestApi.ChatData>> observer;

        public void setView(View view) {
            this.view = view;
        }

        public MatchList() {
            matchBody = new ArrayList<MatchRequestApi.ChatData>();
            observer =  new Observer<List<MatchRequestApi.ChatData>>() {
                @Override
                public void onChanged(List<MatchRequestApi.ChatData> matches) {
                    if (!matches.isEmpty()) {
                        matchBody = (ArrayList<MatchRequestApi.ChatData>) matches;
                        if (matchBody.get(matchBody.size() - 1).id == -1) {
                            Toast.makeText(getContext(), "Ошибка при загрузке данных, попробуйте позже", Toast.LENGTH_LONG).show();
                        } else {
                            MatchRequestApi.ChatData match = matchBody.get(1);
                            TextView name = view.findViewById(R.id.match_name);
                            String res = match.partner.name;
                            name.setText(res);
                        }
                    }
                }
            };
        }
    }
}