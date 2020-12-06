package com.example.and_project.Model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "pokemons_table")
public class Pokemon {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @SerializedName("name")
    public String pokemonName;
    @SerializedName("url")
    public String imageUrl;

    public Pokemon() {

    }

    public Pokemon(int id, String pokemonName, String imageUrl) {
        this.id = id;
        this.pokemonName = pokemonName;
        this.imageUrl = imageUrl;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


}
