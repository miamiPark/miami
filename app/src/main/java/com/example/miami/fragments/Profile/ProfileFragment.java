package com.example.miami.fragments.Profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miami.R;
import com.example.miami.fragments.Gallery.GalleryFragment;
import com.example.miami.models.user.User;
import com.example.miami.viewModels.ProfileViewModel;
import com.squareup.picasso.Picasso;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class ProfileFragment extends Fragment {
    private ProfileViewModel profileViewModel;
    private User user;
    private Observer<User> observer;

    public ProfileFragment() {}

    public static ProfileFragment newInstance() {
        ProfileFragment fragment = new ProfileFragment();
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
//        return inflater.inflate(R.layout.fragment_profile, container, false);

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        observer = new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if (user == null) {
                    Toast.makeText(getContext(), "Ошибка при загрузке данных, попробуйте позже", Toast.LENGTH_LONG).show();
                } else {
                    draw(view, user);

                    ImageView image = view.findViewById(R.id.profileIcon);

                    image.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.w("галерея", "галерея здесь");
                            requireActivity()
                                    .getSupportFragmentManager()
                                    .beginTransaction()
                                    .replace(R.id.content_feed, new GalleryFragment(user.allPhotos))
                                    .commit();
                            Log.w("галерея здесь", "и галерея здесь");
                        }
                    });
                }
            }
        };

        profileViewModel = new ViewModelProvider(getActivity()).get(ProfileViewModel.class);

        profileViewModel.getUser().observe(getViewLifecycleOwner(), observer);

        return view;
    }

    public void draw(View view, User user) {
        ImageView image = view.findViewById(R.id.profileIcon);

        Picasso.with(getContext())
                .load(user.photo)
                .into(image);

        TextView profileInfo = view.findViewById(R.id.profileInfo);
        String result = user.name + ", " + user.dateOfBirth;
        profileInfo.setText(result);
    }
}