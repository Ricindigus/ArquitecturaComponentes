package com.example.arquitecturacomponentes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FutbolDatabase db;
    private JugadorAdapter jugadorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler);
        db = FutbolDatabase.getInstance(this);
        jugadorAdapter = new JugadorAdapter(this, new JugadorAdapter.OnClick() {
            @Override
            public void onClick(View view, int position) {

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(jugadorAdapter);
    }

    public void agregarJugador(View view) {
        startActivity(new Intent(MainActivity.this,JugadorActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        jugadorAdapter.setItems(db.futbolDAO().getAllJugadores());
    }
}
