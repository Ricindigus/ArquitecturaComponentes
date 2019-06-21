package com.example.arquitecturacomponentes;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface FutbolDAO {

    @Query("SELECT * FROM jugadores")
    LiveData<List<Jugador>> getAllJugadores();

    @Insert
    void insertJugador(Jugador jugador);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateJugador(Jugador jugador);

    @Delete
    void deleteJugador(Jugador jugador);

    @Query("SELECT * FROM jugadores WHERE id = :idJugador")
    LiveData<Jugador> getJugadorById(int idJugador);

}
