package mx.edu.uteq.dapps.proyecto195.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import mx.edu.uteq.dapps.proyecto195.ProductoActivity;
import mx.edu.uteq.dapps.proyecto195.R;
import mx.edu.uteq.dapps.proyecto195.components.Bici;
import mx.edu.uteq.dapps.proyecto195.components.ProductoAdapter;

public class CatalogoFragment extends Fragment {

    //Agregamos un ArrayList de tipo bici
    private List<Bici> bicis;

    private ListView lvCatalogo;
    private ProductoAdapter productoAdapter;

    private SwipeRefreshLayout srlCatalogo;

    private RequestQueue conServidor;
    private StringRequest petServidor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Guardamos una referencia al fragmento
        View fragmentView = inflater.inflate(R.layout.fragment_catalogo, container, false);

        //inicializamos el ListView
        lvCatalogo = fragmentView.findViewById(R.id.lv_catalogo);

        //Inicializamos el SwipeRefresh
        srlCatalogo = fragmentView.findViewById(R.id.srl_catalogo);

        conServidor = Volley.newRequestQueue(getContext());

        /*
        Creamos algunos objetos de tipo bici para ejemplicar
        nuestro listview
         */
        bicis = new ArrayList<>();

        /*
        Indicar el estado del SwipeRefesh como ocupado (cargando)
         */
        srlCatalogo.post(new Runnable() {
            @Override
            public void run() {
                srlCatalogo.setRefreshing(true);

                /* Cargamos los productos desde el Webservice */
                cargaProductos();
            }
        });

        /*
        Actualizamos la lista a arrastrarla
         */
        srlCatalogo.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                srlCatalogo.setRefreshing(true);
                cargaProductos();
            }
        });

        /*
        Vinculamos el adaptador con el listview
        */
        productoAdapter = new ProductoAdapter(getActivity(), bicis);
        lvCatalogo.setAdapter(productoAdapter);

        /*
        Evento touch / click de un elemento del ListView de catálogo
         */
        lvCatalogo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Creamos un TextView para recuperar el id del producto
                final TextView tvProductoId = view.findViewById(R.id.tv_producto_id);
                final String productoId = tvProductoId.getText().toString();

                /*
                Es posible enviar valores entre activities por medio de un Extra
                eso significsa que podemos compartir datos entre pantallas

                putExtra("nombre_valor", valor);
                 */

                //Lanzar el activity Detalle para
                //mostrar el detalle de cada producto
                startActivity(
                    new Intent(
                        getContext(),
                        ProductoActivity.class
                    )
                    .putExtra("id", productoId)
                );
            }
        });

        return fragmentView;
    } //onCreateView

    //Metodo para cargar películas desde un Servicio usando Volley
    public void cargaProductos() {
        petServidor = new StringRequest(
                Request.Method.GET,
                "https://zavaletazea.dev/api-195/catalogo/lista",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        /* si la respuesta es correcta verificamos
                        que existan productos */
                        try {
                            JSONObject respuesta = new JSONObject(response);

                            /* Si la respuesta si tiene productos */
                            if (respuesta.getInt("response_code") == 200) {
                                /*
                                Preraramos un arreglo de productos
                                 */
                                JSONArray productos = respuesta.getJSONArray("productos");

                                /*
                                Por cada elemento en al arreglo de productos
                                lo pasamos a la lista de elementos de bicicletas
                                 */
                                bicis = new ArrayList<>();
                                Bici bici;
                                for(int i = 0; i < productos.length(); i++) {
                                    /* Por cada elemento en el arreglo JSON
                                    * debemos crear un objeto de tipo bici y
                                    * agregarlo a la lista de bicicletas */
                                    JSONObject objBici = productos.getJSONObject(i);

                                    bici = new Bici();
                                    bici.setBiciId(objBici.getInt("producto_id"));
                                    bici.setImagen(objBici.getString("foto"));
                                    bici.setModelo(objBici.getString("modelo"));
                                    bici.setCategoria(objBici.getString("categoria"));
                                    bici.setPrecio(objBici.getDouble("precio"));
                                    bici.setActivo(objBici.getInt("activo"));

                                    /* agregarlo a la lista de productos */

                                    bicis.add(bici);
                                }

                                /* actualizamos graficamente los elementos
                                de la lista
                                 */
                                productoAdapter = new ProductoAdapter(
                                        getActivity(),
                                        bicis
                                );
                                lvCatalogo.setAdapter(productoAdapter);
                                productoAdapter.notifyDataSetChanged();

                                srlCatalogo.setRefreshing(false);
                            }
                        }
                        catch(Exception e) {
                            Toast.makeText(getActivity(),
                                    e.getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            srlCatalogo.setRefreshing(false);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(),
                                error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        //Ejecutamos el servicio
        conServidor.add(petServidor);
    }
}