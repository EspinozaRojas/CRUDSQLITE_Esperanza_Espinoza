package modal;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.example.crudsqlite_esperanza_espinoza.MainActivity;
import com.example.crudsqlite_esperanza_espinoza.R;
import entidades.ConexionSQLite;
import entidades.DTO;
public class Modal {
    Dialog dial;
    AlertDialog.Builder diag;
    boolean validarInput = false;
    String codigo, descr, precio;
    SQLiteDatabase db = null;
    DTO datos = new DTO();

    public void  Search(final Context context){
        dial = new Dialog(context);
        dial.setContentView(R.layout.ventana1);
        dial.setCancelable(false);
        final ConexionSQLite conexion = new ConexionSQLite(context);
        final EditText codfinal = (EditText)dial.findViewById(R.id.btnBuscar);
        Button buscarr = (Button)dial.findViewById(R.id.btnBuscar);
        ImageButton cancel = (ImageButton)dial.findViewById(R.id.cancelbusc);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dial.dismiss();
            }
        });
        buscarr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (codfinal.getText().toString().length()== 0){
                    validarInput = false;
                    codfinal.setError("rellene el campo");
                }else {
                    validarInput = true;
                }
                if (validarInput){
                    String cod = codfinal.getText().toString();
                    datos.setCodigo(Integer.parseInt(cod));
                    if (conexion.cosultCod(datos) == true){
                        codigo = String.valueOf(datos.getCodigo());
                        descr = datos.getDescripcion();
                        precio = String.valueOf(datos.getPrecio());
                        Intent ee = new Intent(context, MainActivity.class);
                        ee.putExtra("senale","1");
                        ee.putExtra("codigo",codigo);
                        ee.putExtra("descripci","descripci");
                        ee.putExtra("per",precio);
                        Toast.makeText(context,"Enviado",Toast.LENGTH_SHORT).show();
                        dial.dismiss();
                    }else {
                        Toast.makeText(context,"Los resultados no existen",Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(context,"No has ingresado nada",Toast.LENGTH_SHORT).show();
                }
            }
        });
        dial.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dial.show();
    }
}
