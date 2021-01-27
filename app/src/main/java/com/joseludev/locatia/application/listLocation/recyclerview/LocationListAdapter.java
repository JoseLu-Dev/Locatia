package com.joseludev.locatia.application.listLocation.recyclerview;

import android.app.Activity;
import android.location.Location;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.joseludev.locatia.domain.models.LocationMinimal;

public class LocationListAdapter extends ListAdapter<LocationMinimal, LocationViewHolder> {

    private Activity activity;
    private Location currentLocation;
    public LocationListAdapter(@NonNull DiffUtil.ItemCallback<LocationMinimal> diffCallback, Activity activity, Location currentLocation) {
        super(diffCallback);
        this.activity = activity;
        this.currentLocation = currentLocation;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return LocationViewHolder.create(parent, activity);
    }

    @Override
    public void onBindViewHolder(LocationViewHolder holder, int position) {
        LocationMinimal current = getItem(position);
        holder.bind(current.getName(), current.getDistanceTo(currentLocation), current.getPhotoPath());
    }

    public static class LocationDiff extends DiffUtil.ItemCallback<LocationMinimal> {

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
