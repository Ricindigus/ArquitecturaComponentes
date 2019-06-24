package com.example.arquitecturacomponentes;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

@TypeConverters(DateConverter.class)
@Database(entities = {Jugador.class},version = 1,exportSchema = false)
public abstract class FutbolDatabase extends RoomDatabase {
    private static FutbolDatabase sInstance;
    private static final String NAME_DATABASE = "futbol";
    private static final Object LOCK = new Object();

    public static FutbolDatabase getInstance(Context context){
        if (sInstance == null){
            synchronized (LOCK){
                sInstance = Room.databaseBuilder(context.getApplicationContext(),
                        FutbolDatabase.class, NAME_DATABASE)
                        .build();
            }
        }
        return sInstance;
    }
    abstract public FutbolDAO futbolDAO();
}
