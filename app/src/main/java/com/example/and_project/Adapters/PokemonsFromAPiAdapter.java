package com.example.and_project.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.and_project.Model.Pokemon;
import com.example.and_project.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonsFromAPiAdapter extends RecyclerView.Adapter<PokemonsFromAPiAdapter.ViewHolder> {

    private List<Pokemon> pokemons = new ArrayList<>();

    @NonNull
    @Override
    public PokemonsFromAPiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater lInflater = LayoutInflater.from(parent.getContext());
        View view = lInflater.inflate(R.layout.pokemon_listitem, parent, false);
        return new ViewHolder(view);
    }
/*
    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        notifyDataSetChanged();
    }

 */

    @Override
    public void onBindViewHolder(@NonNull PokemonsFromAPiAdapter.ViewHolder holder, int position) {
        if (pokemons != null) {
            holder.pokemonName.setText(pokemons.get(position).getPokemonName());

        }
    }

    @Override
    public int getItemCount() {
        if (pokemons != null) {
            return pokemons.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView pokemonName;

        ViewHolder(View itemView) {
            super(itemView);
            pokemonName = itemView.findViewById(R.id.pokemonName);
        }
    }
}