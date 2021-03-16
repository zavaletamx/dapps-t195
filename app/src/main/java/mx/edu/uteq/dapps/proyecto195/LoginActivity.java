package mx.edu.uteq.dapps.proyecto195;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private TextInputEditText tietUsername;
    private TextInputEditText tietPin;
    private ProgressDialog progress;
    private AlertDialog.Builder alerta;

    private RequestQueue conexionServidor;
    private StringRequest peticionServidor;

    private final String BASE_URL = "https://zavaletazea.dev/api-195/";

    /*
    Para acceder al archivo global SharedPreferences necesitamos
    una referencia a un objeto de tipo SharedPeferences
     */
    private SharedPreferences prefs;

    /*
    Para escribir (agregar, modificar o eliminar) una entrada de SharedPreferences
    Necesitamos un atributo de tipo Editor
     */
    private SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        /* Vincular elementos del layout con la prog */
        tietUsername = findViewById(R.id.tiet_username);
        tietPin      = findViewById(R.id.tiet_pin);

        progress = new ProgressDialog(LoginActivity.this);
        alerta = new AlertDialog.Builder(LoginActivity.this);

        conexionServidor = Volley.newRequestQueue(LoginActivity.this);

        /*
        Inicializamos la referencia al archivo SharedPreferences
        El método getSharedPreferences usa dos parámetros:
        1.- El nombre del workspace
        2.- El tipo de apertura
         */
        prefs = getSharedPreferences("ma_datos", MODE_PRIVATE);

        //Generamos una referencia de edición
        prefsEditor = prefs.edit();

        /*
        Una vez que se generó la referencia a nuestro espacio de trabajo
        Podemos agregar claves/valores por medio del método .set{_TIPO_DATOI_}()
        Podemos leer claves/valores por medio del método .get{_TIPO_DATOI_}()
         */

        //Guardamos el id del usuario, pero si no existe, guardamos nulo en la variable
        final String idUsuario = prefs.getString("id", null);

        /*
        Si el valor de idUsuario ES DIFERENTE A NULO
        Redireccionamos al Home
         */
        if (idUsuario != null) {
            startActivity(new Intent(
                    LoginActivity.this,
                    MainActivity.class
            ));
        }


    } //ONCREATE

    /**
     * Método para ir al home (Drawer)
     */
    public void navHome(View v) {
        startActivity(new Intent(
                LoginActivity.this,
                MainActivity.class
        ));
    } //NAVHOME

    /**
     * Método para ir al registro
     */
    public void navRegistro(View v) {
        startActivity(new Intent(
                LoginActivity.this,
                RegistroActivityWs.class
        ));
    } //NAVREGISTRO

    public void navListView(View v) {
        startActivity(new Intent(
                LoginActivity.this,
                EjemploListviewActivity.class
        ));
    } //NAVLISTVIEW

    public void navWebService(View v) {
        startActivity(new Intent(
                LoginActivity.this,
                EjemploWebServiceActivity.class
        ));
    } //NAVWEBSERVICE

    /**
     * Método para iniciar sesión desde un usuario en una bd remota
     * por medio de un servicio web
     */
    public void login(View v) {
        /* TODO: FALTA Validar campos */

        progress.setTitle("Autenticando");
        progress.setMessage("Por favor espera...");
        progress.setIndeterminate(true);
        progress.setCancelable(false);
        progress.show();

        /*
        Consumir el servicio de login
         */
        peticionServidor = new StringRequest(
                //Metodo de envio
                Request.Method.POST,
                //URL del servicio
                BASE_URL + "auth/login",
                //Acciones cuando el servidor contesta sin errores
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /* crear un objeto json para tomar la respuesta */
                        progress.hide();
                        try {
                            JSONObject objRespuesta = new JSONObject(response);

                            /* si el response_code es 200
                            significa que el usuario y la contraseña son correctos
                             */
                            if (objRespuesta.getInt("response_code") == 200) {
                                /*
                                Creamos un objeto de los datos del usuario
                                 */
                                JSONObject datosUsuario = objRespuesta.getJSONObject("datos_usuario");

                                /*
                                Escribimos en nuestro archivo SP
                                Los valores del servicio
                                 */
                                prefsEditor.putString("id", datosUsuario.getString("id"));
                                prefsEditor.putString("tel", datosUsuario.getString("tel"));

                                /*
                                Grabamos los cambios en el archivo
                                 */
                                prefsEditor.commit();

                                alerta.setTitle("Bienvenido")
                                        .setMessage("Hola de nuevo\n" + datosUsuario.getString("tel"))
                                        .setPositiveButton("Ingresar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                            }
                                        })
                                        .setCancelable(false)
                                        .show();

                            }

                            /* si el response code es 404
                            significa que el usuario / contraseña son incorrectos
                             */
                            if (objRespuesta.getInt("response_code") == 404) {
                                alerta.setTitle("ERROR")
                                        .setMessage("Usuario/contraseña incorrectos")
                                        .setIcon(R.drawable.info)
                                        .setCancelable(false)
                                        .setNeutralButton("Continuar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                tietPin.setText("");
                                                tietUsername.requestFocus();
                                            }
                                        })
                                        .show();
                            }

                            if (objRespuesta.getInt("response_code") == 400) {
                                alerta.setTitle("ERROR")
                                        .setMessage(objRespuesta.getString("errors"))
                                        .setIcon(R.drawable.info)
                                        .setCancelable(false)
                                        .setNeutralButton("Continuar", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                tietUsername.setText("");
                                                tietPin.setText("");
                                                //Regresamos el cursor al campo del usuario
                                                tietUsername.requestFocus();
                                            }
                                        })
                                        .show();
                            }
                        }

                        catch(Exception e) {
                            Toast.makeText(
                                    LoginActivity.this,
                                    e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                },
                /**
                 * Si el servidor contesta con un error
                 * @see https://www.restapitutorial.com/httpstatuscodes.html
                 */
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progress.hide();
                        Toast.makeText(
                                LoginActivity.this,
                                error.toString(),
                                Toast.LENGTH_SHORT).show();
                    }
                }

        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                /* indicar los valores que se van a enviar al servicio */
                params.put("tel", tietUsername.getText().toString());
                params.put("pin", AppHelper.MD5_Hash(tietPin.getText().toString()));
                return params;
            }
        };
        conexionServidor.add(peticionServidor);
    } //login

} //CLASE