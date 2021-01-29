package com.joseludev.locatia.application.dialogs.categories;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.joseludev.locatia.R;
import com.joseludev.locatia.domain.database.LocationRoomDatabase;
import com.joseludev.locatia.domain.models.CategoryDao;
import com.joseludev.locatia.domain.models.CategoryModel;

import java.util.Objects;

public class NewCategoryDialogFragment extends DialogFragment {

    private Activity activity;
    private EditText editTextCategory;

    public NewCategoryDialogFragment() {

    }

    public static NewCategoryDialogFragment newInstance(Activity activity) {
        NewCategoryDialogFragment fragment = new NewCategoryDialogFragment();
        fragment.activity = activity;
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
        View view = inflater.inflate(R.layout.dialog_fragment_new_category, null);

        editTextCategory = view.findViewById(R.id.editTextCategory);

        view.findViewById(R.id.cancel_button).setOnClickListener(v -> {
            this.dismiss();
        });

        view.findViewById(R.id.confirm_button).setOnClickListener(v -> {
            LocationRoomDatabase db = LocationRoomDatabase.getDatabase(activity.getApplication());
            CategoryDao categoryDao = db.categoryDao();
            LocationRoomDatabase.getDatabaseWriteExecutor().execute(() -> {
                categoryDao.insert(new CategoryModel(editTextCategory.getText().toString()));
            });
            this.dismiss();
        });

        builder.setView(view);
        return builder.create();
    }
}