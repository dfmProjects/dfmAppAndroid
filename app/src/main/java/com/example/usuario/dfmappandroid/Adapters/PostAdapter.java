package com.example.usuario.dfmappandroid.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.usuario.dfmappandroid.Objects.Post;
import com.example.usuario.dfmappandroid.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;



    public class PostAdapter extends ArrayAdapter {

        // Atributos
        private RequestQueue requestQueue;
        JsonArrayRequest jsArrayRequest;
        private static final String URL_BASE = "http://servidorexterno.site90.com/datos";
        private static final String URL_JSON = "/social_media.json";
        private static final String TAG = "PostAdapter";
        List<Post> items;

        public PostAdapter(Context context) {
            super(context,0);

            // Crear nueva cola de peticiones
            requestQueue= Volley.newRequestQueue(context);

            //OTRO WEB SERVICE https://reqres.in/api/users/2
            // Nueva petición JSONObject
            jsArrayRequest = new JsonArrayRequest (
                    Request.Method.GET,
                    "http://web3.disfrimur.com:8060/wsdl/REST/service.php?NumModelo=1",
                    null,
                    new Response.Listener<JSONArray >() {
                        @Override
                        public void onResponse(JSONArray  response) {
                            //items = parseJson(response);

                            List<Post> posts = new ArrayList<>();
                            JSONArray jsonArray= null;
                            Toast.makeText(getContext(), response.toString(), Toast.LENGTH_LONG).show();

                            Log.d("Response", response.toString());
                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);
                                    String nickname =
                                            obj.getString("Descripcion");
                                    Log.d("Descripcion",nickname);
                                    Post post = new Post(
                                            obj.getString("Descripcion"),
                                            obj.getString("first_name"),
                                            obj.getString("last_name"));




                                    posts.add(post);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }


                            notifyDataSetChanged();
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());
                            Toast.makeText(getContext(), "Error en JSON: no se puede covertir JSONObject a JSONArray", Toast.LENGTH_SHORT).show();

                        }
                    }
            );

            // Añadir petición a la cola
            requestQueue.add(jsArrayRequest);
        }

        @Override
        public int getCount() {
            return items != null ? items.size() : 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            // Referencia del view procesado
            View listItemView;

            //Comprobando si el View no existe
            listItemView = null == convertView ? layoutInflater.inflate(
                    R.layout.filas_nominas_item,
                    parent,
                    false) : convertView;


            // Obtener el item actual
            Post item = items.get(position);

            // Obtener Views
            TextView textoTitulo = (TextView) listItemView.
                    findViewById(R.id.textoTitulo);
            TextView textoDescripcion = (TextView) listItemView.
                    findViewById(R.id.textoDescripcion);
            final ImageView imagenPost = (ImageView) listItemView.
                    findViewById(R.id.imagenPost);

            // Actualizar los Views
            textoTitulo.setText(item.getTitulo());
            textoDescripcion.setText(item.getDescripcion());

            // Petición para obtener la imagen
            ImageRequest request = new ImageRequest(
                    URL_BASE + item.getImagen(),
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap bitmap) {
                            imagenPost.setImageBitmap(bitmap);
                        }
                    }, 0, 0, null,null,
                    new Response.ErrorListener() {
                        public void onErrorResponse(VolleyError error) {
                            imagenPost.setImageResource(R.drawable.error);
                            Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                        }
                    });

            // Añadir petición a la cola
            requestQueue.add(request);


            return listItemView;
        }

        public List<Post> parseJson(JSONObject jsonObject){
            // Variables locales


            List<Post> posts = new ArrayList<>();
            JSONArray jsonArray= null;


            try {
                // Obtener el array del objeto
                jsonArray = jsonObject.getJSONArray("nomina");

                for(int i=0; i<jsonArray.length(); i++){

                    try {
                        JSONObject objeto= jsonArray.getJSONObject(i);

                        Post post = new Post(
                                objeto.getString("Descripcion"),
                                objeto.getString("first_name"),
                                objeto.getString("last_name"));


                        posts.add(post);

                    } catch (JSONException e) {
                        Log.e(TAG, "Error de parsing: "+ e.getMessage());
                    }
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


            return posts;
        }
    }

