package com.example.usuario.dfmappandroid.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.usuario.dfmappandroid.Pojo.Noticia;
import com.example.usuario.dfmappandroid.R;
import com.example.usuario.dfmappandroid.Utils.Constantes;
import com.example.usuario.dfmappandroid.Utils.Funciones;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class OneNoticia extends BaseActivity {

    private String id_noticia;
    private ProgressBar progressBar;
    final String TAG = "OneNoticia";

    private TextView titleNotice, fecha, bodyNotice, tag, pie;
    private ImageView imagen;
    CardView cv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_noticia);

        cv = (CardView)findViewById(R.id.card_view_noticias);

        titleNotice = (TextView) findViewById(R.id.titleNotice);
        fecha = (TextView) findViewById(R.id.fecha);
        tag = (TextView) findViewById(R.id.tag);
        bodyNotice = (TextView) findViewById(R.id.bodyNotice);
        imagen = (ImageView) findViewById(R.id.imageNotice);
        pie = (TextView) findViewById(R.id.pie);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        Intent mIntent = getIntent();
        id_noticia = mIntent.getStringExtra(Constantes.getIdNoticia());
        getNoticia(id_noticia);
    }


    private void getNoticia(String id) {

        String endPoint = Constantes.getWEBSERVICE() + "?n_id=" + id;
        Log.d(TAG, endPoint);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(endPoint, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Noticia noticia = new Noticia();

                for (int i = 0; i < response.length(); i++)
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        noticia.setTitulo(jsonObject.getString("n_titulo"));
                        noticia.setBody(jsonObject.getString("n_body"));
                        noticia.setPie(jsonObject.getString("n_pie"));
                        noticia.setTag(jsonObject.getString("n_tag"));
                        noticia.setFecha(Funciones.getNombreMes(jsonObject.getString("mes")) + " " +jsonObject.getString("year"));
                        noticia.setImagen(jsonObject.getString("nom_doc"));
                        noticia.setmId(jsonObject.getString("n_id"));
                        noticia.setNom_mes(jsonObject.getInt("nom_mes"));


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Log.e(TAG,  e.getMessage());
                        progressBar.setVisibility(View.GONE);
                    }

                CrearNoticia(noticia);
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, error.toString());
                progressBar.setVisibility(View.GONE);
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    public void CrearNoticia (Noticia notice) {

        titleNotice.setText(notice.getTitulo());
        fecha.setText(notice.getFecha());
        tag.setText(notice.getTag());
        bodyNotice.setText(notice.getBody());
        pie.setText(notice.getPie());
        setColorTag(notice.getTag(), tag);

        Glide.with(this).load(Constantes.getPATH() + notice.getImagen())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        progressBar.setVisibility(View.GONE);
                        cv.setVisibility(View.VISIBLE);
                        return false;
                    }
                })
                .into(imagen);



    }


    // No hamburguer
    @Override
    protected boolean useDrawerToggle() {
        return false;
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }



    public void setColorTag (String tag, View txtTag) {

        GradientDrawable bgShape = (GradientDrawable)txtTag.getBackground();

        switch (tag.toUpperCase()) {

            case "SALUD":
                bgShape.setColor(getResources().getColor(R.color.colorDfm));
                break;
            case "VIDA":
                bgShape.setColor(getResources().getColor(R.color.colorGreen));
                break;
            case "VIAJAR":
                bgShape.setColor(getResources().getColor(R.color.colorLand));
                break;
            default:
                bgShape.setColor(Color.RED);
        }

    }


}
