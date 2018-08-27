package com.example.usuario.dfmappandroid.Objects;

import android.graphics.Bitmap;

public class Noticias {

    public String titulo, body, pie, fecha;

    private String mId;

    Integer image;

    public Noticias() {

    }


    public Noticias(String titulo, String body, String pie, Integer image, String fecha) {
        this.titulo = titulo;
        this.body = body;
        this.pie = pie;
        this.image = image;
        this.fecha = fecha;
    }


    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getPie() {
        return pie;
    }

    public void setPie(String pie) {
        this.pie = pie;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }
}
