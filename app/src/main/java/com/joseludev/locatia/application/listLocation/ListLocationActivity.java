package com.joseludev.locatia.application.listLocation;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joseludev.locatia.R;
import com.joseludev.locatia.application.listLocation.recyclerview.LocationListAdapter;
import com.joseludev.locatia.application.newLocation.NewLocationActivity;

public class ListLocationActivity extends AppCompatActivity {

    private ListLocationViewModel listLocationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        setContentView(R.layout.activity_list_location);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final LocationListAdapter adapter = new LocationListAdapter(new LocationListAdapter.WordDiff(), this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        listLocationViewModel = new ListLocationViewModel(this.getApplication());

        listLocationViewModel.getLocationList().observe(this, locations -> {
            adapter.submitList(locations);
        });
    }

    public void toNewLocationActivity(View view) {
        Intent newLocationActivity = new Intent(this, NewLocationActivity.class);
        startActivity(newLocationActivity);
    }
}