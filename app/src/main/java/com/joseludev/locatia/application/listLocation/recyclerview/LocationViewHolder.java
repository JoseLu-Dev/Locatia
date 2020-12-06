package com.joseludev.locatia.application.listLocation.recyclerview;

import android.media.Image;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.joseludev.locatia.R;

import java.io.File;

public class LocationViewHolder extends RecyclerView.ViewHolder{
    private final TextView textViewName, textViewDistance;
    private final ImageView imageView;

    private LocationViewHolder(View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textViewName);
        textViewDistance = itemView.findViewById(R.id.textViewDistance);
        imageView = itemView.findViewById(R.id.imageView);
    }

    public void bind(String name, Double distance, String photoPath) {
        textViewName.setText(name);
        //textViewDistance.setText(String.valueOf(distance));
        imageView.setImageURI(Uri.fromFile(new File(photoPath)));
    }

    static LocationViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_locations_item, parent, false);
        return new LocationViewHolder(view);
    }
}
