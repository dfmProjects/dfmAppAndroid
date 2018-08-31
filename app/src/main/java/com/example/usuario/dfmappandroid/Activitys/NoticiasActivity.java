package com.example.usuario.dfmappandroid.Activitys;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuario.dfmappandroid.Adapters.MovieAdapter;
import com.example.usuario.dfmappandroid.Adapters.NoticiasAdapter;
import com.example.usuario.dfmappandroid.Objects.Movie;
import com.example.usuario.dfmappandroid.Objects.Noticias;
import com.example.usuario.dfmappandroid.R;
import com.example.usuario.dfmappandroid.Utils.Constantes;
import com.example.usuario.dfmappandroid.Utils.FileDownloader;
import com.example.usuario.dfmappandroid.Utils.RecyclerItemClickListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NoticiasActivity extends BaseActivity {

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;

    private List<Noticias> movieList;
    private RecyclerView.Adapter adapter;

    private String url = "http://web3.disfrimur.com:8060/wsdl/REST/service.php";
    private String id = "?nom_mes=4"; // Simula un select *
    static String TAG = "NoticiasActivity";

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
        adapter = new NoticiasAdapter(getApplicationContext(),movieList);

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
                            intent.putExtra(Constantes.getIdNoticia(), movieList.get(position).getmId());
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

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url+id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++)
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Noticias notice = new Noticias();
                        notice.setTitulo(jsonObject.getString("n_titulo"));
                        notice.setBody(jsonObject.getString("n_body"));
                        notice.setPie(jsonObject.getString("n_pie"));
                        notice.setTag(jsonObject.getString("n_tag"));
                        notice.setFecha(jsonObject.getString("n_fecha"));
                        notice.setImagen(jsonObject.getString("nom_doc"));
                        notice.setmId(jsonObject.getString("n_id"));
                        notice.setNom_mes(jsonObject.getInt("nom_mes"));

                        movieList.add(notice);
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

/*
    private void getData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               /* for (int i = 0; i < response.length(); i++)
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Noticias movie = new Noticias();
                        movie.setBody(jsonObject.getString("empresa"));
                        movie.setTitulo(jsonObject.getString("dpto"));
                        movie.setFecha(jsonObject.getString("delegacion"));
                        movie.setTitulo(jsonObject.getString("nombre"));
                        movie.setTitulo(jsonObject.getString("doc"));

                        movieList.add(movie);
                    } catch (JSONException e) {
                        e.printStackTrace();
                        progressBar.setVisibility(View.GONE);

                        String titulo, String body, String pie, Integer image, String fecha)
                    }
                Noticias movie = new Noticias();

                movieList.add(new Noticias ("1000 lugares que visitar antes de morir",  "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "Pie noticia", R.drawable.img_example, null));

                movieList.add(new Noticias ("En verano, vaija al invierno", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "Pie noticia", R.drawable.img_example2,null));

                movieList.add(new Noticias ("Cabo de Gata, paraíso universal", "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.", "Pie noticia", R.drawable.img_example3,null));

                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressBar.setVisibility(View.GONE);
            }
        });



        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    */



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }



    public void view(String filename)
    {
        File pdfFile = new File(Environment.getExternalStorageDirectory() + "/dfmApp/" + filename);  // -> filename = maven.pdf
        Uri path = Uri.fromFile(pdfFile);
        Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
        pdfIntent.setDataAndType(path, "application/pdf");
        pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        try{
            startActivity(pdfIntent);
        }catch(ActivityNotFoundException e){
            Toast.makeText(this, "No Application available to view PDF", Toast.LENGTH_SHORT).show();
        }
    }

    private class DownloadFile extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {

            String fileUrl = strings[0];   // -> http://maven.apache.org/maven-1.x/maven.pdf
            String fileName = strings[1];  // -> maven.pdf

            Log.d(TAG, strings[0]);
            String extStorageDirectory = Environment.getExternalStorageDirectory().toString();
            File folder = new File(extStorageDirectory, "dfmApp");
            folder.mkdir();

            File pdfFile = new File(folder, fileName);

            try {
                pdfFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.i(TAG, e.getMessage());
            }
            if (FileDownloader.downloadFile(fileUrl, pdfFile)){
                return fileName;
            }else {
                Log.i(TAG, "Error en la descarga");
                return "KO";
            }


        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String fileName) {
            //progressBar.dismiss();
            if(fileName != "KO")
                view(fileName);
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);

        }
    }

}

