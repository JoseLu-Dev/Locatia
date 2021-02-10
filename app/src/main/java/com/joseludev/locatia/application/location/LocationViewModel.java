package com.joseludev.locatia.application.location;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.lifecycle.AndroidViewModel;

import com.joseludev.locatia.domain.database.LocationRoomDatabase;
import com.joseludev.locatia.domain.models.LocationDao;
import com.joseludev.locatia.domain.models.LocationModel;

public class LocationViewModel extends AndroidViewModel {

    private LocationModel locationModel;
    private LocationRoomDatabase db;

    public LocationViewModel(@NonNull Application application, String locationName) {
        super(application);
        db = LocationRoomDatabase.getDatabase(application);
        LocationDao locationDao = db.locationDao();
        LocationRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
            locationModel = locationDao.getLocationSingle(locationName);
        });
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }

    public void deleteLocation(){
        LocationDao locationDao = db.locationDao();
        LocationRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
            locationDao.deleteLocation(locationModel.getId());
        });
    }

    public void openInMaps(Context context) {
        Uri gmmIntentUri = Uri.parse("geo:0,0"
                + "?q=" + locationModel.getLatitude() + "," + locationModel.getLongitude()
                + "(" + locationModel.getName() + ")");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        if (mapIntent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(mapIntent);
        }
    }

    @BindingAdapter("src")
    public static void bindImage(Context context, ImageView imageView, String src) {
        Uri uri = Uri.parse(src);
        imageView.setImageURI(uri);
    }
}
