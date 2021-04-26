package com.example.miami.fragments.Feed;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.models.feed.UserFeed;
import com.example.miami.viewModels.FeedViewModel;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.squareup.picasso.Picasso;


import static android.content.ContentValues.TAG;

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

    private FeedViewModel feedViewModel;
    private Integer numberCard = 0;
    private ArrayList<UserFeed> userFeeds;
    private  Observer<List<UserFeed>> observer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card, container, false);

        observer = new Observer<List<UserFeed>>() {
            @Override
            public void onChanged(List<UserFeed> users) {
                if (!users.isEmpty()) {
                    userFeeds = (ArrayList<UserFeed>) users;
                    if (userFeeds.get(userFeeds.size() - 1).id == -1) {
                        Toast.makeText(getContext(), "Ошибка при загрузке данных, попробуйте позже", Toast.LENGTH_LONG).show();
                    } else {
//                        UserFeed user = userFeeds.get(numberCard);
//                        if (user.linkImages[0] != "") {
//                            ImageView image = view.findViewById(R.id.icon);
//                            image.setImageURI(Uri.parse(user.linkImages[0]));
//                        }
//                        TextView name = view.findViewById(R.id.name);
//                        String res = user.name + ", " + String.valueOf(user.date_birth);
//                        name.setText(res);
//
//                        TextView education = view.findViewById(R.id.education);
//                        education.setText(String.valueOf(user.education));
                        draw(view);

                    }
                } else {
                    Toast.makeText(getContext(), "Загрузки", Toast.LENGTH_LONG).show();
//                        getActivity().getSupportFragmentManager().beginTransaction()
//                                    .replace(R.id.fragment_view, new CardFragment(), null)
//                                    .commit();
                    Log.w("users", "хахха ПУСТОЙ");
                }

            }
        };

        feedViewModel = new ViewModelProvider(getActivity())
                        .get(FeedViewModel.class);

        feedViewModel.getFeed().observe(getViewLifecycleOwner(), observer);

        ImageButton like = view.findViewById(R.id.like);
        ImageButton dislike = view.findViewById(R.id.dislike);

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerLike(view);
            }
        });

        dislike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerDisLike(view);
            }
        });

        return view;
    }

    public void draw(View view) {
        Log.w("draw", "РИСУЮ");
        UserFeed user = userFeeds.get(numberCard);
        if (user.linkImages[0] != "") {
            ImageView image = view.findViewById(R.id.icon);
//            image.setImageBitmap(getImageBitmap(user.linkImages[0]));
//            try {
//                URL newurl = new URL(user.linkImages[0]);
//                Bitmap mIcon_val = BitmapFactory.decodeStream(newurl.openConnection() .getInputStream());
//                image.setImageBitmap(mIcon_val);
//            } catch (IOException e) {
//                Log.e("ERROR:", "Моя ошибка", e);
//            }

//            image.setImageURI(Uri.parse(user.linkImages[0]));
            Picasso.with(getContext())
                    .load(user.linkImages[0])
                    .into(image );
        }
        TextView name = view.findViewById(R.id.name);
        String res = user.name + ", " + String.valueOf(user.date_birth);
        name.setText(res);

        TextView education = view.findViewById(R.id.education);
        education.setText(String.valueOf(user.education));
    }

    private Bitmap getImageBitmap(String url) {
        Bitmap bm = null;
        try {
            URL aURL = new URL(url);
            URLConnection conn = aURL.openConnection();
            conn.connect();
            InputStream is = conn.getInputStream();
            BufferedInputStream bis = new BufferedInputStream(is);
            bm = BitmapFactory.decodeStream(bis);
            bis.close();
            is.close();
        } catch (IOException e) {
            Log.e(TAG, "Error getting bitmap", e);
        }
        return bm;
    }

    public void listenerLike(View view) {
        // Like запрос numberCard

        Log.w("ЛАЙК", "СТАВЛЮ");
        numberCard++;
        if (numberCard == userFeeds.size()) {
            numberCard = 0;
            feedViewModel.getFeed().observe(getViewLifecycleOwner(), observer);
            return;
        }
        draw(view);
    }

    public void listenerDisLike(View view) {
        // DisLike запрос numberCard
        Log.w("ДИЗЛАЙК", "СТАВЛЮ");
        numberCard++;
        if (numberCard == userFeeds.size()) {
            numberCard = 0;
            feedViewModel.getFeed().observe(getViewLifecycleOwner(), observer);
        }
        draw(view);
    }

    private class UserFeedList {
        public View view;
        public ArrayList<UserFeed> userFeeds;
        private Observer<List<UserFeed>> observer;
        private Integer number;

        public void setView(View view) {
            this.view = view;
        }

        public UserFeedList () {
            userFeeds = new ArrayList<UserFeed>();
            observer =  new Observer<List<UserFeed>>() {
                @Override
                public void onChanged(List<UserFeed> users) {
                    if (!users.isEmpty()) {
                        userFeeds = (ArrayList<UserFeed>) users;
                        if (userFeeds.get(userFeeds.size() - 1).id == -1) {
                            Toast.makeText(getContext(), "Ошибка при загрузке данных, попробуйте позже", Toast.LENGTH_LONG).show();
                        } else {
                            work();

                            UserFeed user = userFeeds.get(1);
                            if (user.linkImages[0] != "") {
                                ImageView image = view.findViewById(R.id.icon);
                                image.setImageURI(Uri.parse(user.linkImages[0]));
                            }
                            TextView name = view.findViewById(R.id.name);
                            String res = user.name + ", " + String.valueOf(user.date_birth);
                            name.setText(res);

                            TextView education = view.findViewById(R.id.education);
                            education.setText(String.valueOf(user.education));
                        }
                    }

                }
            };
        }

        public void work() {

        }

        public void update() {
            feedViewModel = new ViewModelProvider(getActivity())
                    .get(FeedViewModel.class);

            feedViewModel.getFeed().observe(getViewLifecycleOwner(), observer);
        }

    }
}