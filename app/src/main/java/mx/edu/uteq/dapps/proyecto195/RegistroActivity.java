package mx.edu.uteq.dapps.proyecto195;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistroActivity extends AppCompatActivity {

    /**
     * Atributos de la clase
     */
    private Spinner spEstados;

    /*
    Para utilizar un Spinner es necesario utilizar:
    1.- Colección de datos (ArrayList)
    2.- Adaptador
     */
    private String[] coleccion = {"- - - - - - - - - -","Aguascalientes" , "Baja California" , "Baja California Sur" ,
            "Campeche" , "Chiapas" , "Chihuahua" , "Coahuila" , "Colima" , "Distrito Federal" ,
            "Durango" , "Estado de México" , "Guanajuato" , "Guerrero" , "Hidalgo" , "Jalisco" ,
            "Michoacán" , "Morelos" , "Nayarit" , "Nuevo León" , "Oaxaca" , "Puebla" , "Querétaro" ,
            "Quintana Roo" , "San Luis Potosí" , "Sin Localidad" , "Sinaloa" , "Sonora" , "Tabasco" ,
            "Tamaulipas" , "Tlaxcala" , "Veracruz" , "Yucatán" , "Zacatecas"};

    /*
    ArrayAdapter necesita inferir el tipo de dato, por lo que no es posible
    utilizar tipos de datos primitivos, debemos usar sus Wrappers
    int - Integer
    float - Float
    double - Double
    char - Character
    boolean - Boolean
     */
    private ArrayAdapter<String> adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        /*
        Vincular los elementos visuales con los elementos de la clase
         */
        spEstados = findViewById(R.id.sp_estado);

        /*
        Inicializar el adaptador con los parámetros iniciales
        1.- Contexto
        2.- Diseño de cada elemento
        3.- Colección de valores
         */
        adaptador = new ArrayAdapter<>(
                RegistroActivity.this,
                android.R.layout.simple_list_item_1,
                coleccion
        );

        /*
        Vincular el adaptador al elemento visual
         */
        spEstados.setAdapter(adaptador);

        /*
        Agregamos el evento click
         */
        spEstados.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String estadoSeleccionado = spEstados.getItemAtPosition(position).toString();
                Toast.makeText(
                        RegistroActivity.this,
                        estadoSeleccionado,
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(
                        RegistroActivity.this,
                        "Nadita...",
                        Toast.LENGTH_SHORT).show();
            }
        });

    } // /ONCREATE
}