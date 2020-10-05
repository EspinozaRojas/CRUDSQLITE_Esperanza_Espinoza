package com.example.crudsqlite_esperanza_espinoza;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import  entidades.DTO;
public class activity_detalle_articulo extends AppCompatActivity{
    private TextView codg, descr, ins;
    private TextView codg1,desc1,mas1, fecha1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_articulos);
        codg = (TextView) findViewById(R.id.tvcod);
        descr = (TextView) findViewById(R.id.tvdesc);
        ins = (TextView) findViewById(R.id.tvprecio);
        codg1 = (TextView) findViewById(R.id.tvcod1);
        desc1 = (TextView) findViewById(R.id.tvdesc1);
        mas1 = (TextView) findViewById(R.id.tvprecio1);
        fecha1 = (TextView) findViewById(R.id.tv_fecha);
        Bundle obj = getIntent().getExtras();
        DTO dto = null;
        if (obj != null){
            dto = (DTO) obj.getSerializable("articulo");
            codg.setText(""+dto.getCodigo());
            descr.setText(dto.getDescripcion());
            ins.setText(""+dto.getPrecio());

            codg1.setText(""+dto.getCodigo());
            desc1.setText(dto.getDescripcion());
            mas1.setText(String.valueOf(dto.getPrecio()));
            fecha1.setText("Fecha de creacion: "+getDateTime());
        }
    }
    private String getDateTime(){
        SimpleDateFormat dates = new SimpleDateFormat(
                "yyyy-MM-dd-HH:mm:ss a", Locale.getDefault());
        Date day = new Date();
        return dates.format(day);
    }
    public  void  vol (View view){
        onBackPressed();
    }
}
