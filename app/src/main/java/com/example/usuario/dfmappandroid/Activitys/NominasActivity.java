package com.example.usuario.dfmappandroid.Activitys;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.example.usuario.dfmappandroid.Objects.Nomina;
import com.example.usuario.dfmappandroid.Adapters.NominaAdapter;
import com.example.usuario.dfmappandroid.R;
import com.example.usuario.dfmappandroid.Utils.Constantes;
import com.example.usuario.dfmappandroid.Utils.FileDownloader;
import com.example.usuario.dfmappandroid.Utils.RecyclerItemClickListener;

public class NominasActivity extends BaseActivity {

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Nomina> nominaList;
    private RecyclerView.Adapter adapter;

    //private String url = "http://www.mocky.io/v2/5b7aefc334000075008ed7a2";
    //private String url = "http://www.mocky.io/v2/5b7af6c73400005f008ed7b2"; // LisT varios
    private String url = "http://web3.disfrimur.com:8060/wsdl/REST/service.php";
    private String id = "?u_cod=15807";
    static String TAG = "NominasActivity";

    private ProgressBar progressBar;


    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_nominas);


        getSupportActionBar().setTitle("NÓMINAS");

        context = this;
        mList = (RecyclerView) findViewById(R.id.main_list);

        nominaList = new ArrayList<>();
        adapter = new NominaAdapter(getApplicationContext(), nominaList);

        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        mList.setHasFixedSize(true);
        mList.setLayoutManager(linearLayoutManager);
        mList.addItemDecoration(dividerItemDecoration);
        mList.setAdapter(adapter);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        // Call to web Service
        getData();

        mList.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), mList ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever
                        String archivoPdf = Constantes.getPATH() + nominaList.get(position).getNom_doc();
                        //Toast.makeText(NominasActivity.this, "Mensaje: " + nominaList.get(position).getDoc(), Toast.LENGTH_SHORT).show();
                        new NominasActivity.DownloadFile().execute(archivoPdf, Uri.parse(archivoPdf).getLastPathSegment());
                        Log.i(TAG,"file " + Uri.parse(archivoPdf).getLastPathSegment());


                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );



    }

    private void getData() {

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url + id, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {


                Toast.makeText(getApplicationContext()," Mensaje response: " + response.toString(), Toast.LENGTH_SHORT).show();

                for (int i = 0; i < response.length(); i++)

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);

                        Nomina nom = new Nomina();

                        nom.setNom_doc(jsonObject.getString("nom_doc"));
                        nom.setNom_mes(jsonObject.getInt("nom_mes"));

                        nominaList.add(nom);
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
                Log.e("Volley", error.toString());
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

            String fileUrl = strings[0];
            String fileName = strings[1];

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