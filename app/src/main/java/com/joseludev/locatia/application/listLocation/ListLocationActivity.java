package com.joseludev.locatia.application.listLocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;

import com.joseludev.locatia.R;
import com.joseludev.locatia.application.listLocation.recyclerview.LocationListAdapter;
import com.joseludev.locatia.application.newLocation.NewLocationActivity;

public class ListLocationActivity extends AppCompatActivity {

    private ListLocationViewModel listLocationViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_location);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final LocationListAdapter adapter = new LocationListAdapter(new LocationListAdapter.WordDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        listLocationViewModel = new ListLocationViewModel(this.getApplication());

        listLocationViewModel.getLocationList().observe(this, locations -> {
            // Update the cached copy of the words in the adapter.
            adapter.submitList(locations);
        });
    }

    public void toNewLocationActivity(View view) {
        Intent newLocationActivity = new Intent(this, NewLocationActivity.class);
        startActivity(newLocationActivity);
    }
}