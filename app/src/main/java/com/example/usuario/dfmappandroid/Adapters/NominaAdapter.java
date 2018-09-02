package com.example.usuario.dfmappandroid.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.usuario.dfmappandroid.Pojo.Nomina;
import java.util.List;

import com.example.usuario.dfmappandroid.R;
import com.example.usuario.dfmappandroid.Utils.Constantes;
import com.example.usuario.dfmappandroid.Utils.Funciones;

public class NominaAdapter extends RecyclerView.Adapter<NominaAdapter.ViewHolder>{

    private Context context;
    private List<Nomina> list;


    public NominaAdapter(Context context, List<Nomina> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_item_nomina, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Nomina doc;
        doc = list.get(position);

        holder.txtMes.setText( Funciones.getNombreMes(doc.getNomMes()) );
        holder.txtYear.setText(doc.getNomYear());



    }

    @Override
    public int getItemCount() {return list.size();}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView txtMes, txtYear;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.card_view_nomina);
            txtMes = (TextView) itemView.findViewById(R.id.txtMes);
            txtYear = (TextView) itemView.findViewById(R.id.txtYear);
        }

        @Override
        public void onClick(View view) {


        }
    }

}