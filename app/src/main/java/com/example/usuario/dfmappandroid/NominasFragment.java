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
    ArrayAdapter<String> mListaNominasAdapter;

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

        String[] leadsNames = {
                "Alexander Pierrot",
                "Carlos Lopez",
                "Sara Bonz",
                "Liliana Clarence",
                "Benito Peralta",
                "Juan Jaramillo",
                "Christian Steps",
                "Alexa Giraldo",
                "Linda Murillo",
                "Lizeth Astrada"
        };

        mListaNominasAdapter = new ArrayAdapter<>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                leadsNames);


        mListaNominas.setAdapter(mListaNominasAdapter);


        return root;
    }
}