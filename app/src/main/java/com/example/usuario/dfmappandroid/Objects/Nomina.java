package com.example.usuario.dfmappandroid.Objects;

public class Nomina {

    public String nom_doc;
    public int nom_mes;
    public Nomina() {

    }

    public Nomina(int nom_mes, String nom_doc) {
        this.nom_mes = nom_mes;
        this.nom_doc = nom_doc;
    }

    public int getNom_mes() {
        return nom_mes;
    }

    public void setNom_mes(int nom_mes) {
        this.nom_mes = nom_mes;
    }

    public String getNom_doc() {
        return nom_doc;
    }

    public void setNom_doc(String nom_doc) {
        this.nom_doc = nom_doc;
    }
}
