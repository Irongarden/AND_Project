package com.example.and_project.ApiService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface PokemonApi {
//https://pokeapi.co/api/v2/pokemon
// https://pokeapi.co/api/v2/pokemon?limit=50&offset=200

    // Not possible to pull out a list of pokemon ???
    @GET("api/v2/pokemon?limit=50&offset=200")
    Call<PokemonResponce> getPokemons();

    @GET("api/v2/pokemon/{name}")
    Call<PokemonResponce> getPokemon(@Path("name") String name);
}
