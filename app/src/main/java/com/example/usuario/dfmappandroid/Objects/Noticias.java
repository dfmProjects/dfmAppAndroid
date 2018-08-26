package com.example.usuario.dfmappandroid.Objects;

import android.graphics.Bitmap;

public class Noticias {

    public String titulo, body, pie;

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

    private String mId;

    Integer image;

    public Noticias() {

    }

    public Noticias(String titulo, String body, String pie, Integer image) {
        this.titulo = titulo;
        this.body = body;
        this.pie = pie;
        this.image = image;
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
