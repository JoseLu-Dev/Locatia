package com.joseludev.locatia.application.location;

import android.app.Application;
import android.content.Context;
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

    public LocationViewModel(@NonNull Application application, String locationName) {
        super(application);
        LocationRoomDatabase db = LocationRoomDatabase.getDatabase(application);
        LocationDao locationDao = db.locationDao();
        LocationRoomDatabase.getDatabaseWriteExecutor().execute(() ->{
            locationModel = locationDao.getLocationSingle(locationName);
        });
    }

    public LocationModel getLocationModel() {
        return locationModel;
    }

    public void setLocationModel(LocationModel locationModel) {
        this.locationModel = locationModel;
    }

    @BindingAdapter("src")
    public static void bindImage(Context context, ImageView imageView, String src){
        Uri uri = Uri.parse(src);
        imageView.setImageURI(uri);
    }
}