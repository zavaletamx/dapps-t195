package mx.edu.uteq.dapps.proyecto195.components;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;

import mx.edu.uteq.dapps.proyecto195.R;

/**
 * Todos los Adaptadores que conectan la parte visual (XML) con
 * la parte lógica (Java) heredan de BaseAdapter,
 * que es una <<clase Abstracta>> por lo que debemos implementar
 * los métodos:
 *
 * getCount
 * getItem
 * getItemId
 * getView
 */
public class ProductoAdapter extends BaseAdapter {

    /**
     * Los atributos necesarios para crear una lista personalizada son:
     * 1.- Contexto (La clase que usará este adaptador)
     * 2.- Lista de valores que queremos mostrar
     * 3.- Un objeto que nos permita agregar un diseño personalizado a
     * la lista
     */
    private Context contexto;
    private List<Bici> bicis;
    private LayoutInflater inflater;

    /**
     * Creamos un constructor que inicialice los elementos anteriores
     */
    public ProductoAdapter(Context contexto, List bicis) {
        this.contexto = contexto;
        this.bicis = bicis;
        inflater = LayoutInflater.from(contexto);
    }

    /**
     * Indicamos cuantos elementos tenemos en nuestra lista
     * de datos
     */
    @Override
    public int getCount() {
        return bicis.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     * Indicamos el diseño que se aplicará PARA CADA ELEMENTO
     * eso quiere decir que este método SE EJECUTA POR CADA ELEMENTO DE LA LISTA
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        /*
        Indicamos que aplicaremos el mismo diseño para todos los elementos
        de la lista
         */
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_producto, null);
        }

        /*
        Vinculamos los elementos de la vista con variables locales
         */
        ImageView ivImagen = convertView.findViewById(R.id.iv_imagen);
        TextView tvModelo = convertView.findViewById(R.id.tv_modelo);
        TextView tvCategoria = convertView.findViewById(R.id.tv_categoria);
        TextView tvCalificacion = convertView.findViewById(R.id.tv_calif);

        /**
         * Mostramos cada elemento de la lista en nuestro layout
         *
         * Para saber qué elemento tomar de nuestro ArrayList utilizamos
         * la variable position
         */
        Picasso.get().load(bicis.get(position).getImagen()).into(ivImagen);
        tvModelo.setText(bicis.get(position).getModelo());
        tvCategoria.setText(bicis.get(position).getCategoria());

        /*
        Dependiendo de la categoría mostramos una imagen u otra
         */
        if (bicis.get(position).getCategoria().equals("Montaña")) {
            tvCategoria.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_image, 0, 0, 0);
        }
        if (bicis.get(position).getCategoria().equals("Ruta")) {
            tvCategoria.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_add_road, 0, 0, 0);
        }

        /*
        Dependiendo del número de la calificación mostramos el mimso
        número de estrellas
         */
        tvCalificacion.setText("");
        for(int i = 1; i <= bicis.get(position).getCalificacion(); i++) {
            tvCalificacion.setText(
                    tvCalificacion.getText().toString() + "★"
            );
        }

        /*
        Mostramos la vista armada
         */
        return convertView;
    }
}
