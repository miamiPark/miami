package com.example.miami.fragments.Feed;

import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.models.feed.UserFeed;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.Observer;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CardFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CardFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CardFragment newInstance(String param1, String param2) {
        CardFragment fragment = new CardFragment();
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
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        Observer<List<UserFeed>> observer = new Observer<List<UserFeed>>() {
            @Override
            public void onChanged(List<UserFeed> users) {
                ArrayList<UserFeed> userFeeds = (ArrayList<UserFeed>) users;
                if (userFeeds.get(userFeeds.size() - 1).id == -1) {
                    Toast.makeText(getContext(), "Ошибка при загрузке данных, попробуйте позже", Toast.LENGTH_LONG).show();
                } else {
                    UserFeed user = userFeeds.get(0);
                    if (user.linkImages[0] != "") {
                        ImageView image = view.findViewById(R.id.icon);
                        image.setImageURI(Uri.parse(user.linkImages[0]));
                    }
                    TextView name = view.findViewById(R.id.name);
                    String res = user.name + user.date_birth;
                    name.setText(user.name + String.valueOf(user.date_birth));
                }
            }
        };



        return view;
    }
}