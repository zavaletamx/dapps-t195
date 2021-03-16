package mx.edu.uteq.dapps.proyecto195.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import mx.edu.uteq.dapps.proyecto195.R;
import mx.edu.uteq.dapps.proyecto195.components.Bici;
import mx.edu.uteq.dapps.proyecto195.components.ProductoAdapter;

public class CatalogoFragment extends Fragment {

    //Agregamos un ArrayList de tipo bici
    private List<Bici> bicis;

    private ListView lvCatalogo;
    private ProductoAdapter productoAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Guardamos una referencia al fragmento
        View fragmentView = inflater.inflate(R.layout.fragment_catalogo, container, false);

        //inicializamos el ListView
        lvCatalogo = fragmentView.findViewById(R.id.lv_catalogo);

        /*
        Creamos algunos objetos de tipo bici para ejemplicar
        nuestro listview
         */
        bicis = new ArrayList<>();

        Bici bici = new Bici();
        bici.setBiciId(1);
        bici.setImagen("https://images.immediate.co.uk/production/volatile/sites/21/2019/03/scott-scale-965-01-1481205297829-1r14z865tf1y-d3d5ff9.jpg");
        bici.setModelo("Scott Scale 960 29''");
        bici.setCategoria("Montaña");
        bici.setCalificacion(4);
        bicis.add(bici);

        bici = new Bici();
        bici.setBiciId(2);
        bici.setImagen("https://singletrackworld.com/wp-content/blogs.dir/23/files/2019/03/stw-2019-03-20-20190320_091548-1.jpg");
        bici.setModelo("Giant Trance 29''");
        bici.setCategoria("Montaña");
        bici.setCalificacion(4);
        bicis.add(bici);

        bici = new Bici();
        bici.setBiciId(3);
        bici.setImagen("https://i0.heartyhosting.com/www.bikemag.com/wp-content/uploads/2018/08/20180731_yeticycleslaunch-051.jpg");
        bici.setModelo("Yeti SB 29''");
        bici.setCategoria("Montaña");
        bici.setCalificacion(5);
        bicis.add(bici);

        bici = new Bici();
        bici.setBiciId(4);
        bici.setImagen("https://cdn-ctstaging.pressidium.com/wp-content/uploads/2020/12/2021-Trek-Emonda-SLR-feature.jpg");
        bici.setModelo("Trek Emonda");
        bici.setCategoria("Ruta");
        bici.setCalificacion(3);
        bicis.add(bici);

        /*
        Vinculamos el adaptador con el listview
         */
        productoAdapter = new ProductoAdapter(getActivity(), bicis);

        lvCatalogo.setAdapter(productoAdapter);

        return fragmentView;
    }
}