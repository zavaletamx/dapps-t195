package mx.edu.uteq.dapps.proyecto195;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private AlertDialog.Builder alerta;

    private SharedPreferences prefs;
    private SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alerta = new AlertDialog.Builder(MainActivity.this);

        /*
        Inicializamos la referencia al archivo SP
         */
        prefs = getSharedPreferences("ma_datos", MODE_PRIVATE);
        prefsEditor = prefs.edit();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio,
                R.id.nav_cuenta,
                R.id.nav_catalogo,
                R.id.nav_lista_deseos,
                R.id.nav_carrito)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    //Vincula el menu izquierdo
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    /**
     * Programamos el click de cada elemento
     * del menu izquiero
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        /*
        Evaluar cual de todos los menus fue el seleccionado
         */
        switch (item.getItemId()) {
            //Si el menu seleccionado es salir
            case R.id.m_salir:
                salir();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Modificamos el comportamiento del boton back para que
     * pregunte si deseamos salir
     */
    @Override
    public void onBackPressed() {
        salir();
    }

    /**
     * Método que muestra la alerta para salir
     */
    public void salir () {
        alerta.setTitle("Cerrar sesiíon")
                .setMessage("¿Realmente deseas salir?")
                .setIcon(R.drawable.salir)
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Salir", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*
                        Eliminar los registros almacenados de este
                        usuario en archivo SharedPreferences

                        Forma 1 (Eliminar un valor específico del workspace)
                        prefsEditor.remove("id");
                        prefsEditor.remove("tel");

                        Forma 2 (Eliminar TODOS LOS VALORES del workspace
                        prefsEditor.clear();
                        */
                        prefsEditor.clear();
                        prefsEditor.commit();

                        startActivity(new Intent(
                                MainActivity.this,
                                LoginActivity.class
                        ));
                    }
                })
                .setCancelable(false)
                .show();
    }
}