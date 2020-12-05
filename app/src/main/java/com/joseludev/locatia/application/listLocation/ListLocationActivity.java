package com.joseludev.locatia.application.listLocation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.joseludev.locatia.R;
import com.joseludev.locatia.application.newLocation.NewLocationActivity;

public class ListLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_location);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
    }

    public void toNewLocationActivity(View view) {
        Intent newLocationActivity = new Intent(this, NewLocationActivity.class);
        startActivity(newLocationActivity);
    }
}