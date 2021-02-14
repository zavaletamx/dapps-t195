package mx.edu.uteq.dapps.proyecto195;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
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

    private AlertDialog.Builder alerta;

    /*
    SwipeRefresh para actualizar el contenido
    del listview
     */
    private SwipeRefreshLayout srlLengProg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_listview);

        srlLengProg = findViewById(R.id.srl_lenprog);

        alerta = new AlertDialog.Builder(this);

        lvLenProg = findViewById(R.id.lv_lp);
        coleccion = new ArrayList<>();

        /* Agregar elementos a una lista */
        for (int i = 0; i <= 1; i++) {
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

        /*
        Click de un item dentro de un ListView
         */
        lvLenProg.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(EjemploListviewActivity.this, position + "", Toast.LENGTH_SHORT).show();
                String elemSel = lvLenProg.getItemAtPosition(position).toString();

                alerta.setTitle("¡Hey!")
                .setMessage("Seleccionaste:\n"+position + " - " + elemSel)
                .setIcon(R.drawable.info)
                .setNeutralButton("Aceptar", null)
                .setPositiveButton(null, null)
                .setNegativeButton(null, null)
                .setCancelable(false)
                .show();

            }
        });

        /*
        Evento long click
         */
        lvLenProg.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                alerta.setTitle("¿Eliminar?")
                        .setMessage("¿Realmente deseas eliminar este elemento?")
                        .setIcon(R.drawable.trash)
                        .setNeutralButton(null, null)
                        .setNegativeButton("No", null)
                        .setPositiveButton("Si, eliminar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //Metodo remove de un ArrayList indicando
                                //la pos a eliminar
                                coleccion.remove(position);

                                //Notificamoa al adaptador
                                //que hay un cambio en la coleccíon
                                adaptador.notifyDataSetChanged();
                            }
                        })
                        .setCancelable(false)
                        .show();
                return false;
            }
        });

        srlLengProg.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                /*
                Acciones a realizar cuando se arrastre y suelte
                la lista
                 */

                //Eliminamos los elementos de la colección
                coleccion.clear();

                //Agregamos a la lista, los lenguaje
                //originales
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

                //Notificar al adaptador
                adaptador.notifyDataSetChanged();


                //ocultar loader
                srlLengProg.setRefreshing(false);
            }
        });

    }// /ONCREATE
}