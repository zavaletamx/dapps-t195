package mx.edu.uteq.dapps.proyecto195;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    /**
     * Método para ir al home (Drawer)
     */
    public void navHome(View v) {
        startActivity(new Intent(
                LoginActivity.this,
                MainActivity.class
        ));
    }

    /**
     * Método para ir al registro
     */
    public void navRegistro(View v) {
        startActivity(new Intent(
                LoginActivity.this,
                RegistroActivity.class
        ));
    }
}