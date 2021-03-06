package mx.edu.uteq.dapps.proyecto195;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

public class RegistroActivityWs extends AppCompatActivity {

    private TextInputEditText tietTel;
    private TextInputEditText tietPin;
    private AlertDialog.Builder alerta;
    private ProgressDialog progress;

    /*
    Variables de Volley
     */
    private RequestQueue conServidor;
    private StringRequest petServidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_ws);

        /*
        Inicializar y vincular elementos de la vista
         */
        tietTel = findViewById(R.id.tiet_tel);
        tietPin = findViewById(R.id.tiet_pin);
        alerta = new AlertDialog.Builder(RegistroActivityWs.this);
        progress = new ProgressDialog(RegistroActivityWs.this);

        conServidor = Volley.newRequestQueue(RegistroActivityWs.this);
    }


    public void navRegistro(View v) {
        progress.setTitle("Conectando");
        progress.setMessage("Por favor espera...");
        progress.setCancelable(false);
        progress.setIndeterminate(true);
        progress.show();

        /*
        Invocamos a la peticion al servidor con la url del registro
         */
        petServidor = new StringRequest(
                Request.Method.POST,
                "https://zavaletazea.dev/api-195/auth/registro",
                //CODIGO PARA EL CASO EXITOSO
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RegistroActivityWs.this, response, Toast.LENGTH_SHORT).show();
                        progress.hide();
                    }
                },
                //CODIGO PARA EL CASO DE ERROR
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegistroActivityWs.this, error.toString(), Toast.LENGTH_SHORT).show();
                        progress.hide();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                /* Creamos un Hash (clave,valor) con los datos que desemos enviar al servicio */
                final String tel = tietTel.getText().toString();
                final String pin = tietPin.getText().toString();

                Map<String, String> params = new HashMap<>();
                /* Agregamos cada elemento como variable del servicio */
                params.put("tel", tel);
                params.put("pin", AppHelper.MD5_Hash(pin));

                return params;
            }
        };
        conServidor.add(petServidor);
    }

    public void navLogin(View v) {
        startActivity(new Intent(
                RegistroActivityWs.this,
                LoginActivity.class
        ));
    }



}