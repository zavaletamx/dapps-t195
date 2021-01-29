package mx.edu.uteq.dapps.prueba195;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DisFActivity extends AppCompatActivity {

    /* atributos de una clase con caracterísiticas particulares
    y cuentan con un modificador de acceso (private/public/protected/default)
    */
    private Button btnRegresar;

    /*
    Método inicial de una Actividad
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Muestra la vista indicada en R.layout. */
        setContentView(R.layout.activity_dis_f);

        /* vincular los atributos de la clase con los componentes de
        Layout (xml) */
        btnRegresar = findViewById(R.id.btn_regresar);

        /* Evento click */
        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(
                        DisFActivity.this,
                        MenuActivity.class
                ));
            }
        });
    }
    /* TERMINA ONCREATE */

    /*
    Método propio para ligar a un evento click
    Ejemplo : public void _NOMBRE_ (View v) { }
    */
    public void miPrimerClick (View v) {
        /* Toast: mensaje de alerta básico */
        /*
        Toast(contexto, texto, duracion).show();
         */

        Toast.makeText(
                DisFActivity.this,
                "Hola mundo",
                Toast.LENGTH_LONG
        ).show();
    }


}