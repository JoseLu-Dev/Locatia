package com.joseludev.locatia.application.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joseludev.locatia.R;

import java.util.Objects;

public class GpsAlertDialogFragment extends DialogFragment {

    private GpsAlertDialogFragment() {

    }

    public static GpsAlertDialogFragment newInstance() {
        GpsAlertDialogFragment fragment = new GpsAlertDialogFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_fragment_gps_alert, null);

        view.findViewById(R.id.cancel_button).setOnClickListener(v -> {
            this.dismiss();
        });

        view.findViewById(R.id.confirm_button).setOnClickListener(v -> {
            Intent callGPSSettingIntent = new Intent(
                    android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(callGPSSettingIntent);
        });

        builder.setView(view);
        return builder.create();
    }

}