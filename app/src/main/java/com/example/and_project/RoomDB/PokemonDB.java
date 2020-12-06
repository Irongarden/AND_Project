package com.example.and_project.RoomDB;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.and_project.Model.Pokemon;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Pokemon.class}, version = 1, exportSchema = false)
public abstract class PokemonDB extends RoomDatabase {

    private static volatile PokemonDB instance;

    public abstract PokemonDao pokemonDao();

    private static final String databaseName = "pdatabase.db";

    private static final int threads = 4;
    public static final ExecutorService dbWriterExecutor = Executors.newFixedThreadPool(threads);

    public static PokemonDB getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    PokemonDB.class, databaseName).fallbackToDestructiveMigration().build();
        }
        return instance;
    }


}
