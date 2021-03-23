package mx.edu.uteq.dapps.proyecto195;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

public class ProductoActivity extends AppCompatActivity {

    private String productoId;

    private RequestQueue conServidor;
    private StringRequest petServidor;

    private SwipeRefreshLayout srlProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producto);

        /*
        Activar boton back en la barra de título
         */
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        /*
        Tomamos el valor del id del producto que nos envían desde el fragment
        anterior

        Para tomar un valor de la pantalla anteiro utilizamos:
        getIntet().get_TIPO_Extra(nombre, valor_defecto)
        El "valor_defecto" se aplicará si la variable no existe o no
        fué enviada
         */
        productoId = getIntent().getStringExtra("id");

        /* inicializamos los elementos
        de la UI
         */
        srlProducto = findViewById(R.id.srl_producto);

        /*
        Invocar servicio
         */
        srlProducto.post(new Runnable() {
            @Override
            public void run() {
                srlProducto.setRefreshing(true);
                cargaProducto();
            }
        });
    } //Oncreate

    /* evento que da vida a las secciones del menu
    superior (derecho/izquierdo)
     */

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*
        Usamos un switch para saber que elemento del menu
        estamos seleccionando (por medio de su id)
         */
        switch(item.getItemId()) {
            //Si el elemento seleccionado es la flecha de retorno
            case android.R.id.home :
                /*finalizamos este activity y retornamos
                al anterior*/
                this.finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*
    Método para consumir el servicio que retorna un producto
     */
    public void cargaProducto() {

    }
} // Clase