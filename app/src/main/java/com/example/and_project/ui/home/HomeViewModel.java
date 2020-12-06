package com.example.and_project.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_project.Model.Pokemon;
import com.example.and_project.Repository.Repository;

public class HomeViewModel extends AndroidViewModel {

    Repository repository;

    public HomeViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
    }

    public void updatePokemon(String s) {
        repository.updatePokemon(s);
    }

    public LiveData<Pokemon> getPokemonFromApi(String name) {
        return repository.getPokemonFromAPi(name);

    }


}