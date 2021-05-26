package com.example.miami.fragments.Feed;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.service.autofill.FieldClassification;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.models.feed.ChatModel;
import com.example.miami.models.feed.UserFeed;
import com.example.miami.network.MatchRequestApi;
import com.example.miami.repository.MatchRepo;
import com.example.miami.viewModels.FeedViewModel;
import com.example.miami.viewModels.MatchViewModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MatchFragment extends Fragment {


    public MatchFragment() {}

    public static MatchFragment newInstance() {
        MatchFragment fragment = new MatchFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ChatModel chats;
    private MatchViewModel matchViewModel;
    private Observer<ChatModel> observer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("МАТЧИ", "CREATE VIEW");
        View view = inflater.inflate(R.layout.fragment_match, container, false);

        observer = new Observer<ChatModel>() {
            @Override
            public void onChanged(ChatModel chatData) {
                Log.w("МАТЧИ", "Observer");
                if (chatData == null) {
                    Toast.makeText(getContext(), "Ошибка при загрузке данных, попробуйте позже", Toast.LENGTH_LONG).show();
                    return;
                }

                if (chatData.data == null || chatData.data.isEmpty()) {
                    requireActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.content_feed, new NoMatchFragment(), null)
                            .commit();
                } else {
                    draw(view, chatData);
                }
            }
        };

        matchViewModel = new ViewModelProvider(getActivity())
                .get(MatchViewModel.class);

        matchViewModel.getMatch().observe(getViewLifecycleOwner(), observer);

        return view;
    }

    public void draw(View view, ChatModel chatModel) {
        Log.w("draaaaaw", "рисуюююююююююююююююю");
        FragmentTransaction transaction =  requireActivity()
                .getSupportFragmentManager()
                .beginTransaction();
        for (ChatModel.ChatData chatData : chatModel.data) {
            transaction
                    .add(R.id.matchList, OneMatch.newInstance(
                            chatData.partner.name + ", " + Integer.toString(chatData.partner.date_birth),
                            chatData.partner.linkImages[0])
                    );
        }
        transaction.commit();
    }
}