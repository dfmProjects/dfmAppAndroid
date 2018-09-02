package com.example.usuario.dfmappandroid.Adapters;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.example.usuario.dfmappandroid.Pojo.Noticia;
import com.example.usuario.dfmappandroid.R;
import com.example.usuario.dfmappandroid.Utils.Constantes;

import java.util.List;

public class NoticiaAdapter extends RecyclerView.Adapter<NoticiaAdapter.ViewHolder> {

    private Context context;
    private List<Noticia> list;


    public NoticiaAdapter(Context context, List<Noticia> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public NoticiaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_noticias, parent, false);
        return new NoticiaAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(NoticiaAdapter.ViewHolder holder, int position) {

        final Noticia noticia = list.get(position);

        RequestQueue requestQueue = null; /*Attach the Pointer for Volley*/

        holder.titleNotice.setText(noticia.getTitulo());
        holder.fecha.setText(noticia.getFecha());
        holder.tag.setText(noticia.getTag());
        holder.bodyNotice.setText(String.valueOf(noticia.getBody()));

        Glide.with(context).load(Constantes.getPATH() + noticia.getImagen()).
                into(holder.imagen);

        setColorTag(noticia.getTag(), holder.tag);

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

    public void setColorTag (String tag, View txtTag) {

        GradientDrawable bgShape = (GradientDrawable)txtTag.getBackground();

        switch (tag.toUpperCase()) {

            case "SALUD":
                bgShape.setColor(context.getResources().getColor(R.color.colorDfm));
                break;
            case "VIDA":
                bgShape.setColor(context.getResources().getColor(R.color.colorGreen));
                break;
            case "VIAJAR":
                bgShape.setColor(context.getResources().getColor(R.color.colorLand));
                break;
            default:
                bgShape.setColor(Color.RED);
        }

    }

}