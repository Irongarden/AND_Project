package com.example.and_project.ui.favorite;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.and_project.Model.Pokemon;
import com.example.and_project.Repository.Repository;

import java.util.List;

public class FavoriteViewModel extends AndroidViewModel {

    private MutableLiveData<String> mText;
    private final Repository repository;

    public FavoriteViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("Favorite Pokemon");
        repository = Repository.getInstance(application);
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<List<Pokemon>> getAllPokemons() {
        return repository.getAllPokemons();
    }

    public void deleteAllPokemons() {
        repository.deleteAllPokemons();
    }
}