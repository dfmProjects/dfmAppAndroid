package com.example.usuario.dfmappandroid.Activitys;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
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

import java.util.ArrayList;
import java.util.List;
import com.example.usuario.dfmappandroid.Objects.Movie;
import com.example.usuario.dfmappandroid.Adapters.MovieAdapter;
import com.example.usuario.dfmappandroid.R;
import com.example.usuario.dfmappandroid.Utils.RecyclerItemClickListener;

import java.util.List;

public class ListMockio extends AppCompatActivity {

    //url de example : https://medium.com/android-grid/how-to-fetch-json-data-using-volley-and-put-it-to-recyclerview-android-studio-383059a12d1e

    private RecyclerView mList;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    private List<Movie> movieList;
    private RecyclerView.Adapter adapter;

    //private String url = "http://www.mocky.io/v2/5b7aefc334000075008ed7a2";
    //private String url = "http://www.mocky.io/v2/5b7af6c73400005f008ed7b2"; // LisT varios
    private String url = "http://web3.disfrimur.com:8060/wsdl/REST/service.php";
    private String id = "?id=32";

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mockio);


        mList = (RecyclerView) findViewById(R.id.main_list);

        movieList = new ArrayList<>();
        adapter = new MovieAdapter(getApplicationContext(),movieList);

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

                        //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://web3.disfrimur.com:8061/empleo/" + movieList.get( position).getDoc().toString()));;
                        //Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://web3.disfrimur.com:8061/empleo/Pedro-Duque-9928328238.pdf"));
                        //startActivity(browserIntent);


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

                        Movie movie = new Movie();
                        movie.setEmpresa(jsonObject.getString("empresa"));
                        movie.setDpto(jsonObject.getString("dpto"));
                        movie.setDelegacion(jsonObject.getString("delegacion"));
                        movie.setNombre(jsonObject.getString("nombre"));
                        movie.setDoc(jsonObject.getString("doc"));

                        movieList.add(movie);
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
}
