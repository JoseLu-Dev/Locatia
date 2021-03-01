package com.joseludev.locatia.domain.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.joseludev.locatia.domain.models.CategoryDao;
import com.joseludev.locatia.domain.models.CategoryModel;
import com.joseludev.locatia.domain.models.Converters;
import com.joseludev.locatia.domain.models.LocationDao;
import com.joseludev.locatia.domain.models.LocationModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {LocationModel.class, CategoryModel.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class LocationRoomDatabase extends RoomDatabase {
    public abstract LocationDao locationDao();
    public abstract CategoryDao categoryDao();

    private static volatile LocationRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
        }
    };

    public static LocationRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (LocationRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            LocationRoomDatabase.class, "location_database").addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static ExecutorService getDatabaseWriteExecutor() {
        return databaseWriteExecutor;
    }
}
