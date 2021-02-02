package mx.edu.uteq.dapps.prueba195;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DisFActivity extends AppCompatActivity {

    /* atributos de una clase con caracterísiticas particulares
    y cuentan con un modificador de acceso (private/public/protected/default)
    */
    private Button btnRegresar;

    /*
    Agregamos un atributo por cada elemento del mismo tipo del
    layout que nececitemos
    manipular
     */
    private EditText etNombreCompleto;

    /*
    Agregamos una alerta para interactuar con el usuario
     */
    private AlertDialog.Builder alerta;

    /*
    Método inicial de una Actividad
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /* Muestra la vista indicada en R.layout. */
        setContentView(R.layout.activity_dis_f);

        /**
         * Vincular los atributos de la clase con los componentes de
         * Layout (xml)
         */
        alerta = new AlertDialog.Builder(DisFActivity.this);
        btnRegresar = findViewById(R.id.btn_regresar);
        etNombreCompleto = findViewById(R.id.et_nombre_completo);

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
    Metodo propio para validar el formulario al dar clic
    en el boton registro
     */
    public void validaFormulario(View v) {
        /*
        1.- Tomar el texto de los campos y guardarlo
        en variables finales

        2.- Evaluar si el contenido cumple con la regla de validación

        3.- En caso de no cumplir, indicar el / los errores
         */
        final String nombreCompleto = etNombreCompleto.getText().toString();

        /*
        alerta.setTitle("ERROR");
        alerta.setMessage("Hola desde una alerta");
        alerta.setIcon(R.drawable.cancelar);
        alerta.setCancelable(false);

        Las alertas de android pueden tener hasta 3 botones
        en las siguientes posiciones:
        neutral            negative    positive

        alerta.setPositiveButton("BOTON POSITIVO", null);
        alerta.setNegativeButton("BOTON NEGATIVO", null);
        alerta.setNeutralButton("BOTON NEUTRO", null);
        alerta.show();
        */

        //Validamos que el nombre tenga de menos 7 digitos
        if (nombreCompleto.trim().length() < 7) {
            alerta.setTitle("ERROR");
            alerta.setMessage("El nombre es incorrecto");
            alerta.setIcon(R.drawable.cancelar);
            alerta.setCancelable(false);
            alerta.setPositiveButton("Aceptar", null);
            alerta.show();

            //Salimos del método (finalizamos su ejecución)
            return;
        }

        /*
        Validaciones:
        Teléfono (10 dígitos)
        Confirmación tel = telefono
        Pin (6 números(
        Confirmación pin = pin
        E-mail = 5 caracteres y contener un @ y un punto  .
        Confirmación email = email
        Matrícula = 10 dígitos
        Grupo = 5 carcteres
        Último promedio = número entre 8 y 10 (con decimales)
         */

    }

} // /Clase