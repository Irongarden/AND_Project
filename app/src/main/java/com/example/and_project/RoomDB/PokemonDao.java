package com.example.and_project.RoomDB;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.and_project.Model.Pokemon;

import java.util.List;

@Dao
public interface PokemonDao {

    @Insert
    void insert(Pokemon pokemon);

    @Update
    void update(Pokemon pokemon);

    @Delete
    void delete(Pokemon pokemon);

    @Query("DELETE FROM pokemons_table")
    void deleteAllPokemons();

    @Query("SELECT * FROM pokemons_table")
    LiveData<List<Pokemon>> getAllPokemons();

    @Query("SELECT * FROM pokemons_table LIMIT 1")
    LiveData<Pokemon> getPokemon();
}
