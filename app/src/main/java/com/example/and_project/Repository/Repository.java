package com.example.and_project.Repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.and_project.ApiService.PokemonApi;
import com.example.and_project.ApiService.PokemonResponce;
import com.example.and_project.ApiService.ServiceGenerator;
import com.example.and_project.Model.Pokemon;
import com.example.and_project.RoomDB.PokemonDB;
import com.example.and_project.RoomDB.PokemonDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {

    private final PokemonDao pokemonDao;
    private static Repository instance;
    private final LiveData<List<Pokemon>> allPokemons;
    private final MutableLiveData<Pokemon> pokemon;
    private final MutableLiveData<Pokemon> pokemonsFromAPi;
    private String prev;

    private Repository(Application application) {
        pokemonDao = PokemonDB.getInstance(application).pokemonDao();
        allPokemons = pokemonDao.getAllPokemons();
        pokemon = new MutableLiveData<>();
        pokemonsFromAPi = new MutableLiveData<>();
    }

    public static synchronized Repository getInstance(Application application) {
        if (instance == null) {
            instance = new Repository(application);
        }
        return instance;
    }

    public void insertPokemon(Pokemon pokemon) {
        PokemonDB.dbWriterExecutor.execute(() -> {
            pokemonDao.insert(pokemon);
        });
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        PokemonDB.dbWriterExecutor.execute(() -> {
            pokemonDao.getAllPokemons();
        });
        return allPokemons;
    }

    public void deleteAllPokemons() {
        PokemonDB.dbWriterExecutor.execute(() -> {
            pokemonDao.deleteAllPokemons();
        });
    }

    public LiveData<Pokemon> getPokemonFromAPi(String name) {
        PokemonApi pokemonApi = ServiceGenerator.getPokemonApi();
        if (name == prev) {
            System.out.println("prev called");
            return pokemonsFromAPi;
        }
        Call<PokemonResponce> call = pokemonApi.getPokemon(name);
        call.enqueue(new Callback<PokemonResponce>() {
            @Override
            public void onResponse(Call<PokemonResponce> call, Response<PokemonResponce> response) {
                if (response.code() == 200) {
                    pokemonsFromAPi.setValue(response.body().getPokemon());
                    Log.i("Repository", response.body().getPokemon().pokemonName);
                    prev = name;
                }
            }

            @Override
            public void onFailure(Call<PokemonResponce> call, Throwable t) {
                Log.i("Retrofit", "Error getting data from API");
            }
        });
        return pokemonsFromAPi;
    }

    public void updatePokemon(String pokemonName) {
        PokemonApi pokemonApi = ServiceGenerator.getPokemonApi();
        Call<PokemonResponce> call = pokemonApi.getPokemon(pokemonName);
        call.enqueue(new Callback<PokemonResponce>() {
            @Override
            public void onResponse(Call<PokemonResponce> call, Response<PokemonResponce> response) {
                if (response.code() == 200) {
                    pokemon.setValue(response.body().getPokemon());
                    Pokemon newPokemon = new Pokemon();
                    newPokemon.setPokemonName(response.body().getPokemon().pokemonName);
                    newPokemon.setImageUrl(response.body().getPokemon().getImageUrl());
                    insertPokemon(newPokemon);
                }
            }

            @Override
            public void onFailure(Call<PokemonResponce> call, Throwable t) {
                Log.i("Retrofit", "Error getting data from API");
            }
        });
    }
}
