package com.example.arquitecturacomponentes;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class JugadorActivity extends AppCompatActivity {
    EditText etNombre, etClub, etNacionalidad, etEdad;
    TextView tvFechaNacimiento;
    Date fechaNacimiento;
    FutbolDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jugador);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        etClub = findViewById(R.id.etClub);
        etEdad = findViewById(R.id.etEdad);
        tvFechaNacimiento = findViewById(R.id.etNacimiento);
        etNacionalidad = findViewById(R.id.etNacionalidad);
        etNombre = findViewById(R.id.etNombre);
        db = FutbolDatabase.getInstance(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void seleccionarFecha(View view) {
        DatePickerDialog recogerFecha = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                //Esta variable lo que realiza es aumentar en uno el mes ya que comienza desde 0 = enero
                final int mesActual = month + 1;
                //Formateo el día obtenido: antepone el 0 si son menores de 10
                String diaFormateado = (dayOfMonth < 10)? "0" + String.valueOf(dayOfMonth):String.valueOf(dayOfMonth);
                //Formateo el mes obtenido: antepone el 0 si son menores de 10
                String mesFormateado = (mesActual < 10)? "0" + String.valueOf(mesActual):String.valueOf(mesActual);
                //Muestro la fecha con el formato deseado
                tvFechaNacimiento.setText(diaFormateado + "/" + mesFormateado + "/" + year);
                fechaNacimiento = new Date(year-1900,month,dayOfMonth);
            }
            //Estos valores deben ir en ese orden, de lo contrario no mostrara la fecha actual
            /**
             *También puede cargar los valores que usted desee
             */
        }, 2019, 6, 6);
        //Muestro el widget
        recogerFecha.show();
    }

    public void guardarJugador(View view) {
        Jugador jugador = new Jugador(
                etNombre.getText().toString(),
                Integer.parseInt(etEdad.getText().toString()),
                etNacionalidad.getText().toString(),
                etClub.getText().toString(),
                fechaNacimiento
                );

       db.futbolDAO().insertJugador(jugador);
       finish();
    }
}
