package com.example.arquitecturacomponentes;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private LiveData<List<Jugador>> jugadores;

    public MainViewModel(@NonNull Application application) {
        super(application);
        FutbolDatabase futbolDatabase = FutbolDatabase.getInstance(this.getApplication());
        jugadores = futbolDatabase.futbolDAO().getAllJugadores();
    }

    public LiveData<List<Jugador>> getJugadores() {
        return jugadores;
    }
}
