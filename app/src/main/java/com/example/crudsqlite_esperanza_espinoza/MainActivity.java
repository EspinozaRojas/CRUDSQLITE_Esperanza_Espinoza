package com.example.crudsqlite_esperanza_espinoza;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;
import androidx.annotation.RequiresApi;
import entidades.ConexionSQLite;
import entidades.DTO;
import modal.Modal;

public class MainActivity extends AppCompatActivity {
    private EditText etcod,etdesc,etpz;
    private Button btng,btnbcod,btndes,btnmod,btndell;
    private TextView result;
    boolean inputET = false;
    boolean inputED = false;
    boolean input1 = false;
    int resultInsert = 0;
    //activar
    Modal ventanas = new Modal();
    ConexionSQLite conexion = new ConexionSQLite(this);
    DTO dato = new DTO();
    AlertDialog.Builder wiuwiu;

    public boolean onKeyDown(int KeyCode, KeyEvent event){
        if (KeyCode == KeyEvent.KEYCODE_BACK){
            new android.app.AlertDialog.Builder(this)
                    .setIcon(R.drawable.cancel)
                    .setTitle("PELIGROO")
                    .setMessage("¿Desea salir?")
                    .setNegativeButton(android.R.string.cancel,null)
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finishAffinity();
                        }
                    })
                    .show();
            return true;
        }
        return  super.onKeyDown(KeyCode,event);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.bacl));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitleMargin(0,0,0,0);
        toolbar.setSubtitle("CRUD SQLite");
        toolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        toolbar.setTitle("Julissa");
        setSupportActionBar(toolbar);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comfirmacion();
            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ventanas.Search(MainActivity.this);
            }
        });
        etcod = (EditText) findViewById(R.id.et1);
        etdesc = (EditText) findViewById(R.id.et2);
        etpz = (EditText) findViewById(R.id.et3);
        btnbcod = (Button) findViewById(R.id.btnConsuCodigo);
        btndes = (Button) findViewById(R.id.btnConsuDescripcion);
        btng = (Button) findViewById(R.id.btnGuardar);
        btnmod = (Button) findViewById(R.id.btnEditar);
        btndell = (Button) findViewById(R.id.btnBorrar);
        String sign = "";
        String codigo = "";
        String desc = "";
        String precio = "";
        try {
            Bundle bun = getIntent().getExtras();
            if (bun != null){
                String a = (String) bun.get("codigo");
                String b = (String) bun.get("descr");
                String c = (String) bun.get("codigo");
                sign = bun.getString("pe");
                desc = bun.getString("de");
                precio = bun.getString("pe");
                etcod.setText(a);
                etdesc.setText(b);
                etpz.setText(c);
            }
        }catch (Exception o){
        }
    }
    private void comfirmacion(){
        String msm = "¿Quieres salir";
        wiuwiu = new AlertDialog.Builder(MainActivity.this);
        wiuwiu.setIcon(R.drawable.cancel);
        wiuwiu.setTitle("Advertencia");
        wiuwiu.setMessage(msm);
        wiuwiu.setCancelable(false);
        wiuwiu.setPositiveButton("SHI", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.finish();
            }
        });
        wiuwiu.setNegativeButton("ÑO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        wiuwiu.show();
    }
    public void limpiardat(){
        etcod.setText(null);
        etdesc.setText(null);
        etpz.setText(null);
    }
    public void limpiardat2(View view){

        etcod.setText(null);
        etdesc.setText(null);
        etpz.setText(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }else if (id == R.id.action_listaArt){
            Intent spinnerAct = new Intent(MainActivity.this,activity_consulta.class);
            startActivity(spinnerAct);
            return true;
        }else if (id == R.id.action_listaArt1){
            Intent listVAct = new Intent(MainActivity.this,listview_articulos.class);
            startActivity(listVAct);
            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void guardar (View view){
        if (etcod.getText().toString().length()== 0){
            etcod.setError("rellene el campo");
            inputET = false;
        }else {
            inputET = true;
        }
        if (etdesc.getText().toString().length()==0){
            etdesc.setError("rellene el campo");
            inputED = false;
        }else {
            inputED = true;
        }
        if (etpz.getText().toString().length()== 0){
            etpz.setError("rellene el campo");
            input1 = false;
        }else{
            input1 = true;
        }
        if (inputET && inputED && input1){
            try {
                dato.setCodigo(Integer.parseInt(etcod.getText().toString()));
                dato.setDescripcion(etdesc.getText().toString());
                dato.setPrecio(Double.parseDouble(etpz.getText().toString()));
                if (conexion.InsertTradicional(dato)){
                    Toast.makeText(this,"se guardo corectamente",Toast.LENGTH_SHORT).show();
                    limpiardat();
                }else {
                    Toast.makeText(this,"Ya existen los datos"+etcod.getText().toString(),Toast.LENGTH_LONG).show();
                }
            }catch (Exception o){
                Toast.makeText(this,"Existe un error",Toast.LENGTH_SHORT).show();
            }
        }
    }
    public  void mensaje(String msm){
        Toast.makeText(this," "+msm,Toast.LENGTH_SHORT).show();
    }
    public  void consultcod (View view){
        if (etcod.getText().toString().length()== 0){
            etcod.setError("rellene el campo");
            inputET = false;
        }else {
            inputET = true;
        }
        if (inputET){
            String codigo = etcod.getText().toString();
            dato.setCodigo(Integer.parseInt(codigo));
            if (conexion.consultArt(dato)){
                etdesc.setText(dato.getDescripcion());
                etpz.setText(""+dato.getPrecio());
            }else{
                Toast.makeText(this,"El producto no existe",Toast.LENGTH_SHORT).show();
                limpiardat();
            }
        }else{
            Toast.makeText(this,"Ingrese el producto",Toast.LENGTH_SHORT).show();
        }
    }
    public  void consuldesc (View view){
        if (etdesc.getText().toString().length()== 0){
            etdesc.setError("rellene el campo");
            inputED = false;
        }else {
            inputED = true;
        }
        if (inputED){
            String desc = etdesc.getText().toString();
            dato.setDescripcion(desc);
            if (conexion.cosultDesc(dato)){
                etcod.setText(""+dato.getCodigo());
                etdesc.setText(dato.getDescripcion());
                etpz.setText(""+dato.getPrecio());
            }else {
                Toast.makeText(this,"El producto no existe",Toast.LENGTH_SHORT).show();
                limpiardat();
            }
        }else {
            Toast.makeText(this,"ingrese la descriccion de su producto",Toast.LENGTH_SHORT).show();
        }
    }
    public  void bajacod(View view){
        if (etcod.getText().toString().length()== 0){
            etcod.setError("Campo obligatorio");
            inputET = false;
        }else {
            inputET = true;
        }
        if (inputET){
            String codmw = etcod.getText().toString();
            dato.setCodigo(Integer.parseInt(codmw));
            if (conexion.delCod(MainActivity.this,dato)){
                limpiardat();
            }else {
                Toast.makeText(this,"Ingrese el articulo ",Toast.LENGTH_SHORT).show();
                limpiardat();
            }
        }
    }
    public void modi (View view){
        if (etcod.getText().toString().length()== 0){
            etcod.setError("Campo obligatorio");
            inputET = false;
        }else {
            inputET = true;
        }
        if (inputET){
            String cod = etcod.getText().toString();
            String desc = etdesc.getText().toString();
            double precio = Double.parseDouble(etpz.getText().toString());
            dato.setCodigo(Integer.parseInt(cod));
            dato.setDescripcion(desc);
            dato.setPrecio(precio);
            if (conexion.mod(dato)){
                Toast.makeText(this,"Editar",Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this,"No se puede modificar su articulo no existe",Toast.LENGTH_SHORT).show();

            }
        }
    }
}