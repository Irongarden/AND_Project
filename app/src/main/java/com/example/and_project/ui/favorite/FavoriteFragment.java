package com.example.and_project.ui.favorite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.and_project.Adapters.PokemonAdapter;
import com.example.and_project.Adapters.PokemonsFromAPiAdapter;
import com.example.and_project.R;

public class FavoriteFragment extends Fragment {



    private FavoriteViewModel favoriteViewModel;
    RecyclerView favoritesList;
    Button deleteAllButton;
    PokemonAdapter pokemonAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        favoriteViewModel =
                new ViewModelProvider(this).get(FavoriteViewModel.class);
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        favoritesList = root.findViewById(R.id.favoriteRecycler);
        deleteAllButton = root.findViewById(R.id.deleteButton);
        deleteAllButton.setOnClickListener(this::onClickDelete);

        favoritesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        //favoritesList.hasFixedSize();

        pokemonAdapter = new PokemonAdapter();
        favoritesList.setAdapter(pokemonAdapter);

        

        favoriteViewModel.getAllPokemons().observe(getViewLifecycleOwner(),pokemons -> {
            pokemonAdapter.setPokemons(pokemons);
        });
        favoriteViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }

    private void onClickDelete(View view) {
        favoriteViewModel.deleteAllPokemons();
        Toast.makeText(getContext(),"All pokemon deleted",Toast.LENGTH_LONG);
    }
}