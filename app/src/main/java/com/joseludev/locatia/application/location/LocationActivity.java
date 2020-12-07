package com.joseludev.locatia.application.location;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.joseludev.locatia.R;
import com.joseludev.locatia.databinding.ActivityLocationBinding;
import com.joseludev.locatia.domain.models.LocationModel;

public class LocationActivity extends AppCompatActivity {

    private LocationViewModel locationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLocationBinding activityLocationBinding =
                DataBindingUtil.setContentView(this, R.layout.activity_location);

        locationViewModel = new LocationViewModel(
                this.getApplication(),
                getIntent().getStringExtra(LocationModel.LOCATION_NAME));

        activityLocationBinding.setViewModel(locationViewModel);
    }

    public void openInMapsButton(View view){
        locationViewModel.openInMaps(this);
    }
}