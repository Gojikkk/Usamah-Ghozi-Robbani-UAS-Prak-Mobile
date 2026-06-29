package com.example.endemikdb.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.endemikdb.model.Endemik;
import com.example.endemikdb.model.Favorit;

@Database(entities = {Endemik.class, Favorit.class}, version = 3, exportSchema = false)
public abstract class EndemikDatabase extends RoomDatabase {

    private static EndemikDatabase instance;

    public abstract EndemikDao endemikDao();
    public abstract FavoritDao favoritDao(); // TAMBAH INI

    public static synchronized EndemikDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            context.getApplicationContext(),
                            EndemikDatabase.class,
                            "endemik_database"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}