package com.example.usuario.dfmappandroid.Activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuario.dfmappandroid.Adapters.NoticiaAdapter;
import com.example.usuario.dfmappandroid.Pojo.Noticia;
import com.example.usuario.dfmappandroid.R;
import com.example.usuario.dfmappandroid.Utils.Constantes;
import com.example.usuario.dfmappandroid.Utils.Funciones;
import com.example.usuario.dfmappandroid.Utils.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class NoticiaActivity extends BaseActivity {

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;

    private List<Noticia> movieList;
    private RecyclerView.Adapter adapter;

    static String TAG = "NoticiaActivity";

    private ProgressBar progressBar;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noticias);


        getSupportActionBar().setTitle("NOTICIAS");

        context = this;
        mList = (RecyclerView) findViewById(R.id.main_list_noticias);

        movieList = new ArrayList<>();
        adapter = new NoticiaAdapter(getApplicationContext(),movieList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.setAdapter(adapter);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Call to web Service
        getData();

        mList.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), mList ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {

                        // Ver la noticia
                        if(movieList.get(position).getmId()!= "") {
                            Intent intent = new Intent(getApplicationContext(), OneNoticia.class);
                            intent.putExtra(Constantes.getIdNoticia(), movieList.get(position).getmId() + "/");
                            startActivity(intent);
                        }




                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );



    }


    private void getData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Constantes.getAllNoticias(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++)
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Noticia noticia = new Noticia();

                        noticia.setTitulo(jsonObject.getString("titulo").trim());
                        noticia.setBody(jsonObject.getString("body").trim());
                        noticia.setPie(jsonObject.getString("pie").trim());
                        noticia.setTag(jsonObject.getString("tag").trim());
                        noticia.setFecha(Funciones.getNombreMes(jsonObject.getString("mes").trim()) + " " +jsonObject.getString("year").trim());
                        noticia.setImagen(jsonObject.getString("imagen").trim());
                        noticia.setmId(jsonObject.getString("id_noticia").trim());
                        movieList.add(noticia);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressBar.setVisibility(View.GONE);


                    }

                adapter.notifyDataSetChanged();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


}

