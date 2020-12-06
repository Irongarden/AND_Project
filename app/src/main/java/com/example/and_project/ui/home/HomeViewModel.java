package com.example.and_project.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.and_project.Model.Pokemon;
import com.example.and_project.Repository.Repository;

public class HomeViewModel extends AndroidViewModel {

    //private MutableLiveData<String> mText;
    Repository repository;


    public HomeViewModel(Application application) {
        super(application);
        repository = Repository.getInstance(application);
        //mText = new MutableLiveData<>();
       // mText.setValue("This is home fragment");
    }
/*
    public LiveData<Pokemon> getPokemonFromApi(){
        return repository.getPokemonFromApi();
    }

 */

    public void updatePokemon(String s){
        repository.updatePokemon(s);
    }

    public LiveData<Pokemon> getPokemonFromApi(String name) {
        //Log.i("HomeViewModel", repository.getAllPokemonFromAPi().toString());
        return repository.getPokemonFromAPi(name);

    }


}