package com.example.usuario.dfmappandroid;

public class Nomina {

    private String nMes;
    private String nAnyo;
    private String nNomina;
    private String nId;

    public String getnId() {
        return nId;
    }

    public void setnId(String nId) {
        this.nId = nId;
    }

    public Nomina(String nId, String nMes, String nAnyo, String nNomina) {
        this.nId = nId;
        this.nMes = nMes;
        this.nAnyo = nAnyo;
        this.nNomina = nNomina;
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
