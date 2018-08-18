package com.example.usuario.dfmappandroid;

import java.util.*;

public class Nomina_Repository {

    private HashMap<String, Nomina> nominas = new HashMap<String, Nomina>();

    private static final Nomina_Repository ourInstance = new Nomina_Repository();

    public static Nomina_Repository getInstance() {
        return ourInstance;
    }

    private Nomina_Repository() {

        saveNomina(new Nomina("Carlos Ruiz", "Asistente", "Hospital Blue", "Nomina",R.drawable.ic_feb));
        saveNomina(new Nomina("Carlos Lopez", "Asistente", "Hospital Blue", "Nomina",R.drawable.ic_feb));
        saveNomina(new Nomina("Carlos Alfonso", "Asistente", "Hospital Blue", "Nomina",R.drawable.ic_feb));
    }

    private void saveNomina(Nomina nomina) {
        nominas.put(nomina.getnId(), nomina);

    }

    public List<Nomina> getNomina() {
        return new ArrayList<>(nominas.values());
    }
}
