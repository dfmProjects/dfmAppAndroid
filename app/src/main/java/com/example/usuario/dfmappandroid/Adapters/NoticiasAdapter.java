package com.example.usuario.dfmappandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usuario.dfmappandroid.Objects.Noticias;
import com.example.usuario.dfmappandroid.R;

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

        Noticias noticia = list.get(position);

        holder.titleNotice.setText(noticia.getTitulo());
        holder.fecha.setText("Salud");
        holder.bodyNotice.setText(String.valueOf(noticia.getBody()));
        holder.image.setImageResource(noticia.getImage());

    }

    @Override
    public int getItemCount() {return list.size();}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView titleNotice, fecha, bodyNotice;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);

            titleNotice = (TextView) itemView.findViewById(R.id.titleNotice);
            fecha = (TextView) itemView.findViewById(R.id.fecha);
            bodyNotice = (TextView) itemView.findViewById(R.id.bodyNotice);
            image = (ImageView) itemView.findViewById(R.id.imageNotice);
        }

        @Override
        public void onClick(View view) {


        }
    }

}