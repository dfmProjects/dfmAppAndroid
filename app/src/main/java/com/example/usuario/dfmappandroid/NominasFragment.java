package com.example.usuario.dfmappandroid;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/*
 * Vista para las nóminas
 */
public class NominasFragment extends Fragment {

    ListView mListaNominas;
    NominasAdapter mListaNominasAdapter;

    public NominasFragment() {
        // Required empty public constructor
    }

    public static NominasFragment newInstance(/*parámetros*/) {
        NominasFragment fragment = new NominasFragment();
        // Setup parámetros
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            // Gets parámetros
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_nominas, container, false);

        mListaNominas = (ListView) root.findViewById(R.id.nominas_list);

        // Inicializar el adaptador con la fuente de datos.
        mListaNominasAdapter = new NominasAdapter(getActivity(),
                Nomina_Repository.getInstance().getNomina());


        mListaNominas.setAdapter(mListaNominasAdapter);


        return root;
    }
}