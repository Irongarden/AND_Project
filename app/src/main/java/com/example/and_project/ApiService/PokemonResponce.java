package com.example.and_project.ApiService;

import com.example.and_project.Model.Pokemon;

import java.util.ArrayList;

public class PokemonResponce {

    private int id;
    private String name;
    private Sprites sprites;
    private ArrayList<Pokemon> pokemons;

    public Pokemon getPokemon() {
        return new Pokemon(id, name, sprites.front_default);
    }

    public class Sprites {
        private String front_default;
    }

    public class Abilitis{
        private String[] ability;
    }


}
