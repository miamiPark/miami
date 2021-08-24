package com.example.miami.imageGallery;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.miami.R;
import com.example.miami.activities.GallerySinglePhotoActivity;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    private ArrayList<ImageUrl> imageUrls;
    private Context mContext;

    public DataAdapter(Context mContext, ArrayList<ImageUrl> imageUrls) {
        this.mContext = mContext;
        this.imageUrls = imageUrls;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.gallery_image_layout, viewGroup, false);
        return new ViewHolder(view);
    }

    /**
     * gets the image url from adapter and passes to Glide API to load the image
     *
     * @param viewHolder
     * @param i
     */
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Glide.with(mContext).load(imageUrls.get(i).getImageUrl()).fitCenter().into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                ImageUrl url = imageUrls.get(position);

                Intent intent = new Intent(mContext, GallerySinglePhotoActivity.class);
                intent.putExtra(GallerySinglePhotoActivity.EXTRA_URL, url.getImageUrl());
                mContext.startActivity(intent);
            }
        }
    }
}