package com.example.usuario.dfmappandroid;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {


    Button btnRequest;
    Button btnRequest2;
    TextView texto;
    RequestQueue requestQueue;

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    String endPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRequest = (Button) findViewById(R.id.btnRequest);
        texto = (TextView) findViewById(R.id.txtTexto);
        Cache cache = new DiskBasedCache(getCacheDir(),1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache,network);
        requestQueue.start();

        btnRequest.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                //Add params
                endPoint = Constantes.webService + "?NumModelo=209";

                JsonArrayRequest stringRequest = new JsonArrayRequest(Request.Method.GET, endPoint, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.i("Service", endPoint);

                        for (int i = 0;i < response.length(); i++) {
                            JSONObject jsonObject = null;

                            try {
                                jsonObject = response.getJSONObject(i);
                                texto.setText("Denominacion: " + jsonObject.getString("Denominacion").toString());
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Log.i("Error al parsear JSON: ", e.getMessage());
                            }

                        }
                        Toast.makeText(getApplicationContext(),"Response :" + response.toString(), Toast.LENGTH_LONG).show();//display the response on screen
                        //requestQueue.stop();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.i(Constantes.TAG,"Error :" + error.toString());
                    }
                });

                requestQueue.add(stringRequest);
            }
        });





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_main: return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
