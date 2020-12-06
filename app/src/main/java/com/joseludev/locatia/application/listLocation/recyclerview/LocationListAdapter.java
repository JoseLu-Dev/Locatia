package com.joseludev.locatia.application.listLocation.recyclerview;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.joseludev.locatia.domain.models.LocationMinimal;

public class LocationListAdapter extends ListAdapter<LocationMinimal, LocationViewHolder> {
    public LocationListAdapter(@NonNull DiffUtil.ItemCallback<LocationMinimal> diffCallback) {
        super(diffCallback);
    }

    @Override
    public LocationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return LocationViewHolder.create(parent);
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
