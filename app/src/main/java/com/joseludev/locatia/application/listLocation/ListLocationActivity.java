package com.joseludev.locatia.application.listLocation;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.joseludev.locatia.R;
import com.joseludev.locatia.application.listLocation.recyclerview.LocationListAdapter;
import com.joseludev.locatia.application.newLocation.NewLocationActivity;
import com.joseludev.locatia.domain.location.LocationManager;

public class ListLocationActivity extends AppCompatActivity implements LocationManager.LocationManagerHandler {

    private ListLocationViewModel listLocationViewModel;
    private LocationListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        setContentView(R.layout.activity_list_location);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        listLocationViewModel = new ListLocationViewModel(this.getApplication());

        onLocationChanged(null);

        LocationManager.getLocationCurrentCache(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocationManager.getLocationCurrentCache(this);
    }

    public void toNewLocationActivity(View view) {
        Intent newLocationActivity = new Intent(this, NewLocationActivity.class);
        startActivity(newLocationActivity);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_locationslist, menu);
        MenuItem searchItem = menu.findItem(R.id.app_bar_search);

        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setFocusable(false);
        searchView.setQueryHint("Search");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                updateRecyclerViewByName(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        searchView.setOnCloseListener(() -> {
            setRecyclerViewDefaultContent();
            return false;
        });

        return true;
    }

    private void setRecyclerViewDefaultContent(){
        listLocationViewModel.getLocationList().observe(this, locations -> adapter.submitList(locations));
    }

    private void updateRecyclerViewByName(String name){
        listLocationViewModel.getLocationListByName(name).observe(this, locations -> adapter.submitList(locations));
    }

    @Override
    public void onLocationChanged(Location location) {
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        adapter = new LocationListAdapter(new LocationListAdapter.LocationDiff(), this, location);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        setRecyclerViewDefaultContent();
    }

    @Override
    public void onLocationPermissionDenied() {

    }
}