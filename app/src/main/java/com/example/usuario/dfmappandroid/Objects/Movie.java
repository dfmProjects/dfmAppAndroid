package com.example.usuario.dfmappandroid.Objects;

public class Movie {

    public String empresa, nombre, dpto, delegacion, doc;
    public Movie() {

    }

    public Movie(String empresa, String nombre, String dpto, String delegacion, String doc) {
        this.empresa = empresa;
        this.nombre = nombre;
        this.dpto = dpto;
        this.delegacion = delegacion;
        this.doc = doc;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDpto() {
        return dpto;
    }

    public void setDpto(String dpto) {
        this.dpto = dpto;
    }

    public String getDelegacion() {
        return delegacion;
    }

    public void setDelegacion(String delegacion) {
        this.delegacion = delegacion;
    }
}
