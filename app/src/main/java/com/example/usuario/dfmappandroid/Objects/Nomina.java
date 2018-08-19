package com.example.usuario.dfmappandroid.Objects;

import android.media.Image;

public class Nomina {

    private String nMes;
    private String nAnyo;
    private String nNomina;
    private String nId;
    private int mImage;

    public Nomina(String nId, String nMes, String nAnyo, String nNomina, int mImage) {
        this.nId = nId;
        this.nMes = nMes;
        this.nAnyo = nAnyo;
        this.nNomina = nNomina;
        this.mImage = mImage;

    }

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public int getmImage() {
        return mImage;
    }

    public void setmImage(int mImage) {
        this.mImage = mImage;
    }

    public String getnMes() {
        return nMes;
    }

    public void setnMes(String nMes) {
        this.nMes = nMes;
    }

    public String getnAnyo() {
        return nAnyo;
    }

    public void setnAnyo(String nAnyo) {
        this.nAnyo = nAnyo;
    }

    public String getnNomina() {
        return nNomina;
    }

    public void setnNomina(String nNomina) {
        this.nNomina = nNomina;
    }

    @Override
    public String toString() {
        return "Nomina{" +
                "ID='" + nId + '\'' +
                ", Mes='" + nMes + '\'' +
                ", Anio='" + nAnyo + '\'' +
                ", Nomina='" + nNomina + '\'' +
                '}';
    }
}
