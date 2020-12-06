package com.example.and_project.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.and_project.Model.Pokemon;
import com.example.and_project.R;

import java.util.ArrayList;
import java.util.List;

public class PokemonAdapter extends RecyclerView.Adapter<PokemonAdapter.ViewHolder> {


    private List<Pokemon> pokemons = new ArrayList<>();

    @NonNull
    @Override
    public PokemonAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.pokemon_listitem, parent, false);
        return new ViewHolder(view);
    }

    public void setPokemons(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (pokemons != null) {
            holder.name.setText(pokemons.get(position).getPokemonName());
           String url = pokemons.get(position).getImageUrl();
            holder.setImage(url);

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
        TextView name;
        ImageView imageView;
        private View mview;
        public Context mcontext;

        ViewHolder(View view) {
            super(view);
            mview = view;
            name = view.findViewById(R.id.pokemonName);
            imageView = view.findViewById(R.id.pokemonImage);
        }

        public void setImage(final String uri){
            //imageView = mview.findViewById(R.id.pokemonImage);
            Glide.with(itemView.getContext()).load(uri).into(imageView);
        }
    }


}
