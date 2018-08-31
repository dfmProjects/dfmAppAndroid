package com.example.usuario.dfmappandroid.Objects;

import android.graphics.Bitmap;

public class Noticias {

    public String titulo, body, pie, tag, fecha, imagen, mId;

    public Noticias() {

    }

    public Noticias(String titulo, String body, String pie, String tag, String fecha, String imagen, String mId) {
        this.titulo = titulo;
        this.body = body;
        this.pie = pie;
        this.tag = tag;
        this.fecha = fecha;
        this.imagen = imagen;
        this.mId = mId;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getmId() {
        return mId;
    }

    public void setmId(String mId) {
        this.mId = mId;
    }

}
