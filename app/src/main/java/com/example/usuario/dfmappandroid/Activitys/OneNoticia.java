package com.example.usuario.dfmappandroid.Activitys;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
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
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.usuario.dfmappandroid.Objects.Noticias;
import com.example.usuario.dfmappandroid.R;
import com.example.usuario.dfmappandroid.Utils.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OneNoticia extends BaseActivity {

    private String id_noticia;
    private ProgressBar progressBar;
    final String TAG = "OneNoticia";

    private TextView titleNotice, fecha, bodyNotice, tag, tag2, tag3, pie;
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
        tag3 = (TextView) findViewById(R.id.tag2);
        tag2 = (TextView) findViewById(R.id.tag3);
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

                Noticias noticia = new Noticias();

                for (int i = 0; i < response.length(); i++)
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        noticia.setTitulo(jsonObject.getString("n_titulo"));
                        noticia.setBody(jsonObject.getString("n_body"));
                        noticia.setPie(jsonObject.getString("n_pie"));
                        noticia.setTag(jsonObject.getString("n_tag"));
                        noticia.setFecha(getNombreMes(jsonObject.getString("mes")) + " " +jsonObject.getString("year"));
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


    public void CrearNoticia (Noticias notice) {

        titleNotice.setText(notice.getTitulo());
        fecha.setText(notice.getFecha());
        tag.setText(notice.getTag());
        tag2.setText(notice.getTag());
        tag3.setText(notice.getTag());
        bodyNotice.setText(notice.getBody());
        pie.setText(notice.getPie());

        Glide.with(this).load(Constantes.getPATH() + notice.getImagen()).
                into(imagen);

        setColorTag(notice.getTag());

    }

    // No hamburguer
    @Override
    protected boolean useDrawerToggle() {
        return false;
    }

    public String getNombreMes (String mes) {

        String nombreMes = "";

        switch (mes) {

            case "1":
                nombreMes = "Enero";
                break;
            case "2":
                nombreMes = "Febrero";
                break;
            case "3":
                nombreMes = "Marzo";
                break;
            case "4":
                nombreMes = "Abril";
                break;
            case "5":
                nombreMes = "Mayo";
                break;
            case "6":
                nombreMes = "Junio";
                break;
            case "7":
                nombreMes = "Julio";
                break;
            case "8":
                nombreMes = "Agosto";
                break;
            case "0":
                nombreMes = "Septiembre";
                break;
            case "10":
                nombreMes = "Octubre";
                break;
            case "11":
                nombreMes = "Noviembre";
                break;
            case "12":
                nombreMes = "Diciembre";
                break;
                default:
                    nombreMes = "Enero";

        }

        return nombreMes;
    }

    public void setColorTag (String tag) {

        View txtTag = findViewById(R.id.tag);
        View txtTag2 = findViewById(R.id.tag2);
        View txtTag3 = findViewById(R.id.tag3);


        switch (tag.toUpperCase()) {

            case "SALUD":
                txtTag.setVisibility(View.VISIBLE);
                break;
            case "VIDA":
                txtTag2.setVisibility(View.VISIBLE);
                break;
            case "VIAJAR":
                txtTag3.setVisibility(View.VISIBLE);
                break;
            default:
                txtTag.setVisibility(View.VISIBLE);
        }
    }

}
