package com.example.usuario.dfmappandroid.Utils;

import com.example.usuario.dfmappandroid.Objects.Noticias;
import com.example.usuario.dfmappandroid.R;

import java.util.List;
import java.util.*;

/**
 * Repositorio ficticio de leads
 */
public class NoticiasRepository {
    private static NoticiasRepository repository = new NoticiasRepository();
    private HashMap<String, Noticias> noticias = new HashMap<>();

    public static NoticiasRepository getInstance() {
        return repository;
    }

    private NoticiasRepository() {
        saveLead(new Noticias("!00 Lugares que visitar antes de morir", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also", "", R.drawable.img_example));
        saveLead(new Noticias("Noticia circunstancial por lsa nevadas", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also", "Hospital Blue", R.drawable.img_example2));
        saveLead(new Noticias("Las playas de Almeria relucen en agosto", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also", "Electrical Parts ltd", R.drawable.img_example3));
    }

    private void saveLead(Noticias lead) {
        noticias.put(lead.getmId(), lead);
    }

    public List<Noticias> getLeads() {
        return new ArrayList<>(noticias.values());
    }
}

