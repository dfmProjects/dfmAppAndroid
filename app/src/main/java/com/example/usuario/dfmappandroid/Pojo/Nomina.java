package com.example.usuario.dfmappandroid.Pojo;

public class Nomina {

    public String nomDoc, nomMes, nomYear;


    public Nomina() {

    }


    public Nomina(String nomDoc, String nomMes, String nomYear) {
        this.nomDoc = nomDoc;
        this.nomMes = nomMes;
        this.nomYear = nomYear;
    }


    public String getNomDoc() {
        return nomDoc;
    }

    public void setNomDoc(String nomDoc) {
        this.nomDoc = nomDoc;
    }

    public String getNomMes() {
        return nomMes;
    }

    public void setNomMes(String nomMes) {
        this.nomMes = nomMes;
    }

    public String getNomYear() {
        return nomYear;
    }

    public void setNomYear(String nomYear) {
        this.nomYear = nomYear;
    }
}
