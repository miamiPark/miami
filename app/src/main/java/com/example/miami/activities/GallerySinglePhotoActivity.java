package com.example.miami.activities;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.miami.R;

public class GallerySinglePhotoActivity extends AppCompatActivity {

    public static final String EXTRA_URL = "GallerySinglePhotoActivity.URL";
    private ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_photo_gallery);

        mImageView = (ImageView) findViewById(R.id.image);
        String imageUrl = getIntent().getStringExtra(EXTRA_URL);

        Glide.with(this)
                .load(imageUrl)
                .asBitmap()
                .into(mImageView);
    }
}