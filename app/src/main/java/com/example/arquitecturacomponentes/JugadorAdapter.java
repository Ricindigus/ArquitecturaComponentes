package com.example.arquitecturacomponentes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JugadorAdapter extends RecyclerView.Adapter<JugadorAdapter.JugadorHolder> {
    private List<Jugador> items;
    private Context context;
    private OnClick onClick;

    public JugadorAdapter(Context context, OnClick onClick) {
        this.context = context;
        this.onClick = onClick;
        this.items = new ArrayList<>();
    }

    public interface OnClick{
        void onClick(View view, int position);
    }

    public void setItems(List<Jugador> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public List<Jugador> getItems() {
        return items;
    }

    @NonNull
    @Override
    public JugadorHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new JugadorHolder(LayoutInflater
                .from(viewGroup.getContext())
                .inflate(R.layout.item_jugador,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull JugadorHolder jugadorHolder, final int i) {
        Jugador currentJugador = items.get(i);
        jugadorHolder.setTvNombre(currentJugador.getNombre());
        jugadorHolder.setTvEdad(currentJugador.getEdad());
        jugadorHolder.setTvClub(currentJugador.getClub());
        jugadorHolder.setTvNacionalidad(currentJugador.getNacionalidad());
        jugadorHolder.setTvNacimiento(currentJugador.getNacimiento());
        jugadorHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClick.onClick(v,i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class JugadorHolder extends RecyclerView.ViewHolder {
        private TextView tvNombre;
        private TextView tvEdad;
        private TextView tvNacionalidad;
        private TextView tvClub;
        private TextView tvNacimiento;
        private View viewJugador;

        public JugadorHolder(@NonNull View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.item_jugador_nombre);
            tvEdad = itemView.findViewById(R.id.item_jugador_edad);
            tvNacionalidad = itemView.findViewById(R.id.item_jugador_nacionalidad);
            tvClub = itemView.findViewById(R.id.item_jugador_club);
            tvNacimiento = itemView.findViewById(R.id.item_jugador_nacimiento);
        }

        public void setTvNombre(String nombre) {
            tvNombre.setText(nombre);
        }

        public void setTvEdad(int edad) {
            tvEdad.setText(String.valueOf(edad));
        }

        public void setTvNacionalidad(String nacionalidad) {
            tvNacionalidad.setText(nacionalidad);
        }

        public void setTvClub(String club) {
            tvClub.setText(club);
        }

        public void setTvNacimiento(Date nacimiento) {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = dateFormat.format(nacimiento);
            tvNacimiento.setText(strDate);
        }
    }
}
