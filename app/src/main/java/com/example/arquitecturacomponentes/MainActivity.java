package com.example.arquitecturacomponentes;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FutbolDatabase db;
    private JugadorAdapter jugadorAdapter;
    public static final String ID_JUGADOR = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        db = FutbolDatabase.getInstance(this);
        jugadorAdapter = new JugadorAdapter(this, new JugadorAdapter.OnClick() {
            @Override
            public void onClick(View view, int position) {
                int idJugador = jugadorAdapter.getItems().get(position).getId();
                Intent intent = new Intent(MainActivity.this,JugadorActivity.class);
                intent.putExtra(ID_JUGADOR,idJugador);
                startActivity(intent);
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(jugadorAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position = viewHolder.getAdapterPosition();
                db.futbolDAO().deleteJugador(jugadorAdapter.getItems().get(position));
                displayJugadores();
            }
        }).attachToRecyclerView(recyclerView);
        displayJugadores();
    }

    public void agregarJugador(View view) {
        startActivity(new Intent(MainActivity.this,JugadorActivity.class));
    }


    private void displayJugadores() {
        LiveData<List<Jugador>> jugadors= db.futbolDAO().getAllJugadores();
        jugadors.observe(this, new Observer<List<Jugador>>() {
            @Override
            public void onChanged(@Nullable List<Jugador> jugadors) {
                jugadorAdapter.setItems(jugadors);
            }
        });
    }
}
