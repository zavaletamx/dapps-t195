package mx.edu.uteq.dapps.prueba195;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //equivalente public static void main de Java SE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); //invoca al constructor del padre
        //R = res (Resources)
        //layout -> carpeta
        //activity_main -> archivo xml
        setContentView(R.layout.activity_main);

        //Accion del boton
    }
}