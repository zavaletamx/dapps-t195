package mx.edu.uteq.dapps.proyecto195;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class EjemploWebServiceActivity extends AppCompatActivity {

    /*
    Para utilizar contenido desde la nube por medio de WebServices es necesario
    configurar lo siguiente:
    1.- Agregar el permiso de internet a nuestra App
    2.- Indicar que utilizaremos origenes inseguros (http)
    3.- *NO RECOMENDADO* AsyncTask  ----*Recomendado* utilizar
        volley/retrofit para las peticiones remotas
    4.- Configurar la librería desde el Gradle para las peticiones remotas

    5.- Para crear una peticion a una URL necesitamos:
    Objeto Volley (Requestqueue) - Conexion al servidor
    Objeto StringRequest - Petición al servidor
     */
    private RequestQueue conServidor;
    private StringRequest petServidor;

    //Ventana modal que muestra un loader
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ejemplo_web_service);

        conServidor = Volley.newRequestQueue(this);
        pd = new ProgressDialog(this);

        //Mostrarmos una ventana en estado de carga
        pd.setTitle("Cargando");
        pd.setMessage("Por favor espera...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.show();

    }
}