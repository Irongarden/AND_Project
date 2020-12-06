package com.example.and_project.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.and_project.Adapters.PokemonsFromAPiAdapter;
import com.example.and_project.R;
import com.google.firebase.auth.FirebaseAuth;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    Button favButton;
    ImageView imageView;
    TextView tv;
    TextView tv2;
    Button searchButton;
    EditText etName;
    RecyclerView pokemonsFromApi;
    PokemonsFromAPiAdapter pokemonsFromAPiAdapter;
    private String name;

    private static final String TAG = "SFragment";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        View root = inflater.inflate(R.layout.fragment_home, container, false);
            tv = root.findViewById(R.id.textView2);
           // tv2 = root.findViewById(R.id.abilitytext);
        favButton = root.findViewById(R.id.button_addToFav);


        favButton.setOnClickListener(this::onFavClick);
        searchButton = root.findViewById(R.id.button1);
        imageView = root.findViewById(R.id.imageView);
        etName = root.findViewById(R.id.etpokemon);
        //pokemonsFromApi = root.findViewById(R.id.pokemonsFromAPI);
        pokemonsFromAPiAdapter = new PokemonsFromAPiAdapter();

        //pokemonsFromApi.setLayoutManager(new LinearLayoutManager(getActivity()));
        //pokemonsFromApi.hasFixedSize();
        //pokemonsFromApi.setAdapter(pokemonsFromAPiAdapter);
        searchButton.setOnClickListener(this::searchPok);
        homeViewModel.getPokemonFromApi("pikachu").observe(getViewLifecycleOwner(), pokemons -> {
            name =pokemons.pokemonName;

            //pokemonsFromAPiAdapter.setPokemons(pokemons);
            //searchButton.setOnClickListener(this::searchPok);
            //searchPok(getView());
            //etName.setText(pokemons.getPokemonName());
            //Log.i("HomeFragment",pokemons.get(0).toString());
            etName.setText(pokemons.getPokemonName());
        });



       /*
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });


        */
        //searchButton.setOnClickListener(this::searchPok);

        return root;
    }

    private void onFavClick(View view) {
        String text = etName.getText().toString();
        homeViewModel.updatePokemon(text);
        if(!(text.equals(""))){
            System.out.println(text + " Added");
        }
    }


    private void searchPok(View view) {
        homeViewModel.getPokemonFromApi(etName.getText().toString());
        //tv.setText(homeViewModel.getPokemonFromApi(etName.getText().toString()).getValue().getPokemonName());
        tv.setText(name);

        //Glide.with(getViewLifecycleOwner()).load(homeViewModel.getPokemonFromApi().getValue().getImageUrl()).into(imageView);
        //Glide.with(view).load(homeViewModel.getPokemonFromApi(etName.getText().toString()).getValue().getImageUrl()).into(imageView);
    }

}