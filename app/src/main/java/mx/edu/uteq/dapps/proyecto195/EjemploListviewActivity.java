package mx.edu.uteq.dapps.proyecto195;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class EjemploListviewActivity extends AppCompatActivity {

    /*
    Para vincular un listview con sus datos necesitamos:
    1.- adaptador
    2.- colección
     */
    private ListView lvLenProg;
    private ArrayAdapter<String> adaptador;
    /*
    Las listas son estructuras de datos ligadas (arreglos dinámicos)
    Que permiten modificar su tamaño y contenido en tiempo de ejecución
     */
    private List<String> coleccion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_listview);

        lvLenProg = findViewById(R.id.lv_lp);
        coleccion = new ArrayList<>();

        /* Agregar elementos a una lista */
        for (int i = 0; i <= 100; i++) {
            coleccion.add("Java");
            coleccion.add("C++");
            coleccion.add("Python");
            coleccion.add("Objective-C");
            coleccion.add("Swift");
            coleccion.add("React");
            coleccion.add("JavaScript");
            coleccion.add("Dart");
            coleccion.add("Flutter");
            coleccion.add("PHP");
        }

        /*
        Adaptador:
        1.- Contexto
        2.- Diseño de cada elemento
        3.- Colección de datos
         */
        adaptador = new ArrayAdapter<>(
                EjemploListviewActivity.this,
                android.R.layout.simple_list_item_1,
                coleccion
        );

        lvLenProg.setAdapter(adaptador);
    }
}