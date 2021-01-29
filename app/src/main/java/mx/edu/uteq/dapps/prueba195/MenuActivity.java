package mx.edu.uteq.dapps.prueba195;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    } //ONCREATE

    /*
    Método para el click de los botones del menu
     */
    public void menu(View v) {
        /*
        La nevagión en Android funciona a partir de un componente
        llamado Intent que evalua si las pantallas son parte del esquema
        de navegación (Que esten en el Manifest.xml)

        Para usar un intent necesitamos:
        1.- CLASE_ACTUAL.this
        2.- CLASE_A_DONDE_VOY.class
         */

        /* Formas de usar un intent */
        /*
        Forma 1
        Intent i = new Intent(MenuActivity.this, DisFActivity.class);
        startActivity(i);*/

        /*
        Forma 2
        startActivity(
                new Intent(
                        MenuActivity.this,
                        DisFActivity.class
                )
        );*/

        /*
        EL metodo click contiene un parametro de tipo View que a su vez contiene
        toda la información del componente que ha dado click
         */

        /*
        Switch para evaluar quien dio click
        */

        Intent intent;
        switch (v.getId()) {
            case R.id.btn_menu_inicio :
                intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_menu_linear :
                intent = new Intent(MenuActivity.this, EjemploLinearActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_menu_formulario :
                intent = new Intent(MenuActivity.this, DisFActivity.class);
                startActivity(intent);
                break;
        }

    }

    /*
    Podemos modificar el comportamiento del boton de retorno (BackButton) sobreescribiendo
    el método onBackPressed
     */

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Operación no permitida", Toast.LENGTH_SHORT).show();
        return;
    }
} // CLASE
