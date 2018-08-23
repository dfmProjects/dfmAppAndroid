package com.example.usuario.dfmappandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.dfmappandroid.Objects.Movie;
import com.example.usuario.dfmappandroid.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    private Context context;
    private List<Movie> list;

    public MovieAdapter(Context context, List<Movie> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Movie movie = list.get(position);

        holder.textEmpresa.setText(movie.getEmpresa());
        holder.textDpto.setText(String.valueOf(movie.getDpto()));
        holder.textDelegacion.setText(String.valueOf(movie.getDelegacion()));
        holder.textNombre.setText(String.valueOf(movie.getNombre()));

    }

    @Override
    public int getItemCount() {return list.size();}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textEmpresa, textDpto, textDelegacion, textNombre;

        public ViewHolder(View itemView) {
            super(itemView);

            textEmpresa = (TextView) itemView.findViewById(R.id.main_empresa);
            textDpto = (TextView) itemView.findViewById(R.id.main_dpto);
            textDelegacion = (TextView) itemView.findViewById(R.id.main_delegacion);
            textNombre = (TextView) itemView.findViewById(R.id.main_nombre);
        }

        @Override
        public void onClick(View view) {


        }
    }

}