package com.example.usuario.dfmappandroid.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.dfmappandroid.Objects.Nomina;
import com.example.usuario.dfmappandroid.R;

import java.util.List;

public class NominaAdapter extends RecyclerView.Adapter<NominaAdapter.ViewHolder> {

    private Context context;
    private List<Nomina> list;

    public NominaAdapter(Context context, List<Nomina> list) {
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

        Nomina nom = list.get(position);

        holder.textYear.setText(String.valueOf(nom.getNom_mes()));
        holder.textDoc.setText(nom.getNom_doc());
        //holder.textDelegacion.setText(String.valueOf(nom.getDelegacion()));

    }

    @Override
    public int getItemCount() {return list.size();}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textYear, textDoc, imgMes;

        public ViewHolder(View itemView) {
            super(itemView);

            textYear = (TextView) itemView.findViewById(R.id.txtYear);
            textDoc = (TextView) itemView.findViewById(R.id.txtNombre);
            imgMes = (TextView) itemView.findViewById(R.id.imgMes);
        }

        @Override
        public void onClick(View view) {


        }
    }

}