package com.example.usuario.dfmappandroid.Objects;

public class Nomina {

    public String nomDoc;
    public int nomMes;

    public Nomina() {

    }

    public Nomina(String nomDoc, int nomMes) {
        this.nomDoc = nomDoc;
        this.nomMes = nomMes;
    }

    public String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public int getNomMes() {
        return nomMes;
    }

    public void setNomMes(int nomMes) {
        this.nomMes = nomMes;
    }
}
