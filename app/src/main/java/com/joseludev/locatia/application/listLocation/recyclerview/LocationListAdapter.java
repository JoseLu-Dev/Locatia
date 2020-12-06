package com.joseludev.locatia.application.listLocation.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.joseludev.locatia.domain.models.LocationMinimal;

public class LocationListAdapter extends ListAdapter<LocationMinimal, LocationViewHolder> {

    private Context context;
    public LocationListAdapter(@NonNull DiffUtil.ItemCallback<LocationMinimal> diffCallback, Context context) {
        super(diffCallback);
        this.context = context;
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return LocationViewHolder.create(parent, context);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        LocationMinimal current = getItem(position);
        holder.bind(current.getName(), 0.0, current.getPhotoPath());
    }

    public static class WordDiff extends DiffUtil.ItemCallback<LocationMinimal> {

        @Override
        public boolean areItemsTheSame(@NonNull LocationMinimal oldItem, @NonNull LocationMinimal newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull LocationMinimal oldItem, @NonNull LocationMinimal newItem) {
            return oldItem.getName().equals(newItem.getName());
        }
    }
}
