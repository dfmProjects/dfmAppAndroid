package com.example.usuario.dfmappandroid.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.usuario.dfmappandroid.Objects.Noticias;
import com.example.usuario.dfmappandroid.R;
import com.example.usuario.dfmappandroid.Utils.Constantes;

import java.util.List;

public class NoticiasAdapter extends RecyclerView.Adapter<NoticiasAdapter.ViewHolder> {

    private Context context;
    private List<Noticias> list;


    public NoticiasAdapter(Context context, List<Noticias> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public NoticiasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_noticias, parent, false);
        return new NoticiasAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticiasAdapter.ViewHolder holder, int position) {

        final Noticias noticia = list.get(position);

        RequestQueue requestQueue = null; /*Attach the Pointer for Volley*/

        holder.titleNotice.setText(noticia.getTitulo());
        holder.fecha.setText("Agosto 2018");
        holder.tag.setText("Salud");
        holder.bodyNotice.setText(String.valueOf(noticia.getBody()));

        Glide.with(context).load(Constantes.getPATH() + noticia.getImagen()).
                into(holder.imagen);

    }

    @Override
    public int getItemCount() {return list.size();}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView titleNotice, fecha, bodyNotice, tag;
        public ImageView imagen;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);

            titleNotice = (TextView) itemView.findViewById(R.id.titleNotice);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            tag = (TextView) itemView.findViewById(R.id.tag);
            bodyNotice = (TextView) itemView.findViewById(R.id.bodyNotice);
            imagen = (ImageView) itemView.findViewById(R.id.imageNotice);

            cv = (CardView)itemView.findViewById(R.id.card_view_noticias);
        }

        @Override
        public void onClick(View view) {


        }
    }

}