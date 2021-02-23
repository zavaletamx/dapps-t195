package mx.edu.uteq.dapps.proyecto195;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

public class EjemploWebServiceActivity extends AppCompatActivity {

    private ImageView ivAvatar;
    private TextView tvNombre;
    private TextView tvEdad;

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

        ivAvatar = findViewById(R.id.iv_avatar);
        tvNombre = findViewById(R.id.tv_nombre);
        tvEdad = findViewById(R.id.tv_edad);

        conServidor = Volley.newRequestQueue(this);
        pd = new ProgressDialog(this);

        //Mostrarmos una ventana en estado de carga
        pd.setTitle("Cargando");
        pd.setMessage("Por favor espera...");
        pd.setIndeterminate(true);
        pd.setCancelable(false);
        pd.show();

        /*
        Petición al servidor se realiza por medio de un objeto de tipo
        StringRequest y se necesitan los siguientes parámetros
        1.-  Tipo de petición (GET, POST, *PUT, *DELETE)
        2.-  Indicar la url del servicio
        3.-  Indicar que debo hacer si la conexión es satisfactoria
        4.-  Indicar que debo hacer si la conexión no pudo llevarse a cabo
        5*.- (Opcional, solo si usamos POST) indicamos los parámetros post
         */
        petServidor = new StringRequest(
                Request.Method.GET,
                // "https://zavaletazea.dev/json.php",
                "http://192.168.0.15/json.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /* Intentamos convertir a response en un objeto json */
                        try {
                            JSONObject objRespuesta = new JSONObject(response);
                            final String nombre = objRespuesta.getString("nombre");
                            final String apellido = objRespuesta.getString("apellido");
                            final int edad = objRespuesta.getInt("edad");
                            final String urlAvatar = objRespuesta.getString("avatar");

                            /* Mostramos una imagen dede una url en iun ImageView */
                            Picasso
                                    .get()
                                    .load(urlAvatar)
                                    .into(ivAvatar);

                            /* la dirección es un objeto */
                            final JSONObject objDireccion = objRespuesta.getJSONObject("direccion");

                            /* los idiomas son un arreglo */
                            final JSONArray arrIdiomas = objRespuesta.getJSONArray("idiomas");

                            tvNombre.setText(nombre + " " + apellido + " " + arrIdiomas.get(2).toString());
                            tvEdad.setText(edad + " años " + objDireccion.getString("calle"));

                            /* Al finalizar de extraer las claves del serivico */

                            //Ocultamos el progressDialog
                            pd.hide();
                            //Destrumos el Progress
                            pd.dismiss();

                        }
                        catch(Exception e) {
                            Toast.makeText(
                                    EjemploWebServiceActivity.this,
                                    e.getMessage(),
                                    Toast.LENGTH_LONG
                            ).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(
                                EjemploWebServiceActivity.this,
                                error.toString(),
                                Toast.LENGTH_LONG
                        ).show();
                    }
                }
        );
        // Ejecutamos el StringRequest en la conexion al servidor
        conServidor.add(petServidor);
    }
}