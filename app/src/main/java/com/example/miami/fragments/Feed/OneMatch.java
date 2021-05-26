package com.example.miami.fragments.Feed;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.miami.R;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OneMatch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OneMatch extends Fragment {

    private static final String NAME_AGE = "Анна, 19";
    private static final String PHOTO = "";

    private String mNameAge;
    private String mPhoto;

    public OneMatch() {
        // Required empty public constructor
    }

    public static OneMatch newInstance(String nameAge, String photo) {
        OneMatch fragment = new OneMatch();
        Bundle args = new Bundle();
        args.putString(NAME_AGE, nameAge);
        args.putString(PHOTO, photo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mNameAge = getArguments().getString(NAME_AGE);
            mPhoto = getArguments().getString(PHOTO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_one_match, container, false);

        TextView nameAge = view.findViewById(R.id.nameAge);
        nameAge.setText(mNameAge);

        if (!mPhoto.equals("")) {
            ImageView image = view.findViewById(R.id.add_photo);

            Picasso.with(getContext())
                    .load(mPhoto)
                    .into(image);
        }

        return view;
    }
}