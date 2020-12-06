package com.example.and_project.ApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    //https://pokeapi.co/api/v2/pokemon
    private static final Retrofit.Builder retrofitBuilder = new Retrofit.Builder().
            baseUrl("https://pokeapi.co/").addConverterFactory(GsonConverterFactory.create());

    private static final Retrofit retrofit = retrofitBuilder.build();
    private static final PokemonApi pokemonApi = retrofit.create(PokemonApi.class);

    public static PokemonApi getPokemonApi() {
        return pokemonApi;
    }


}
