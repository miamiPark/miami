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
import com.example.miami.models.feed.ChatModel;
import com.example.miami.models.feed.UserFeed;
import com.example.miami.network.MatchRequestApi;
import com.example.miami.repository.MatchRepo;
import com.example.miami.viewModels.FeedViewModel;
import com.example.miami.viewModels.MatchViewModel;
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

    private ChatModel Chats;
    private MatchViewModel matchViewModel;
    private  Observer<ChatModel> observer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("МАТЧИ", "CREATE VIEW");
        View view = inflater.inflate(R.layout.fragment_match, container, false);
        TextView textView = view.findViewById(R.id.match_name);
        textView.setText("Anna");

        observer = new Observer<ChatModel>() {
            @Override
            public void onChanged(ChatModel chatData) {
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

//    public void draw(View view) {
//        Log.w("draw", "РИСУЮ");
//        MatchRequestApi.ChatData chat = Chats.get(1);
//        TextView name = view.findViewById(R.id.match_item);
//        String res = chat.partner.name + ", " + chat.messages[0];
//        name.setText(res);
//    }
}