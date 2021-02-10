package com.joseludev.locatia.application.location;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        locationViewModel = new LocationViewModel(
                this.getApplication(),
                getIntent().getStringExtra(LocationModel.LOCATION_NAME));

        activityLocationBinding.setViewModel(locationViewModel);
    }

    public void openInMapsButton(View view){
        locationViewModel.openInMaps(this);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_location, menu);
        MenuItem deleteItem = menu.findItem(R.id.app_bar_delete);

        deleteItem.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                locationViewModel.deleteLocation();
                finish();
                //TODO sure yes no dialog
                return false;
            }
        });

        return true;
    }
}