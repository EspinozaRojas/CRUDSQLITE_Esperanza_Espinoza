package com.example.crudsqlite_esperanza_espinoza;


import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import entidades.ConexionSQLite;
import entidades.DTO;

public class activity_consulta extends AppCompatActivity {
    private Spinner espin;
    private TextView cod,dec,sueño;
    ConexionSQLite conexion = new ConexionSQLite(this);
    DTO dato = new DTO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        espin = (Spinner)findViewById(R.id.spinn);
        cod = (TextView) findViewById(R.id.spincodg);
        dec = (TextView) findViewById(R.id.spindescripc);
        sueño = (TextView) findViewById(R.id.pin);
        conexion.consultarListaArticulos();
        ArrayAdapter<CharSequence> adap = new ArrayAdapter(this,android.R.layout.simple_spinner_item,conexion.obtenerListaArticulos());
        espin.setAdapter(adap);
        espin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    cod.setText("Codigo: "+conexion.consultarListaArticulos().get(i-1).getCodigo());
                    dec.setText("Descripcion: "+conexion.consultarListaArticulos().get(i-1).getDescripcion());
                    sueño.setText("Precio: "+conexion.consultarListaArticulos().get(i-1).getPrecio());
                }else
                {
                    cod.setText("Codigo esta Vacio");
                    dec.setText("Descripción esta Vacio");
                    sueño.setText("Precio esta Vacio");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }
}