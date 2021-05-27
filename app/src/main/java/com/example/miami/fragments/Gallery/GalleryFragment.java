package com.example.miami.fragments.Gallery;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miami.R;
import com.example.miami.imageGallery.DataAdapter;
import com.example.miami.imageGallery.ImageUrl;
import com.example.miami.network.UsersApi;
import com.example.miami.viewModels.FeedViewModel;

import java.util.ArrayList;

public class GalleryFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String[] mImages;

    public GalleryFragment(String[] photos) {
        super();
        mImages = photos;
    }

    public static GalleryFragment newInstance(String[] images) {
        return new GalleryFragment(images);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Observer<UsersApi.User> observer;
    private UsersApi.User profile;
    private FeedViewModel feedViewModel;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.w("моя галерея", "CREATE VIEW");
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

//        imageView = (ImageView) findViewById(R.id.imageView);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        ArrayList imageUrlList = prepareData(mImages);
        DataAdapter dataAdapter = new DataAdapter(getContext(), imageUrlList);
        recyclerView.setAdapter(dataAdapter);

        return view;
    }

    private ArrayList prepareData(String[] imageLinks) {

        ArrayList imageUrlList = new ArrayList<>();
        for (int i = 0; i < imageLinks.length; i++) {
            ImageUrl imageUrl = new ImageUrl();
            imageUrl.setImageUrl(imageLinks[i]);
            imageUrlList.add(imageUrl);
        }
        Log.d("gallery", "List count: " + imageUrlList.size());
        return imageUrlList;
    }

    public void draw(View view) {
        Log.w("draw", "РИСУЮ");
//        MatchRequestApi.ChatData chat = Chats.get(1);
//        TextView name = view.findViewById(R.id.match_item);
//        String res = chat.partner.name + ", " + chat.messages[0];
//        name.setText(res);
    }

}