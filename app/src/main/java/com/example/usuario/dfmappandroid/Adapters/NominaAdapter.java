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
import com.example.usuario.dfmappandroid.Objects.Nomina;
import java.util.List;

import com.example.usuario.dfmappandroid.R;
import com.example.usuario.dfmappandroid.Utils.Constantes;

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

        // Nombre del archivo
        String nombreDoc = Constantes.getPATH() + doc.getNomDoc();
        holder.textNomDoc.setText( Uri.parse(nombreDoc).getLastPathSegment());

        // Icono del mes del documento
        int mes = doc.getNomMes();

        switch (mes) {


            case 1:
                holder.imgMes.setImageResource(R.mipmap.ic_ene);
                break;
            case 2:
                holder.imgMes.setImageResource(R.mipmap.ic_feb);
                break;
             case 3:
                holder.imgMes.setImageResource(R.mipmap.ic_mar);
                break;
            case 4:
                holder.imgMes.setImageResource(R.mipmap.ic_abr);
                break;
            case 5:
                holder.imgMes.setImageResource(R.mipmap.ic_may);
                break;
            case 6:
                holder.imgMes.setImageResource(R.mipmap.ic_jun);
                break;
            case 7:
                holder.imgMes.setImageResource(R.mipmap.ic_jul);
                break;
            case 8:
                holder.imgMes.setImageResource(R.mipmap.ic_ago);
                break;
            case 9:
                holder.imgMes.setImageResource(R.mipmap.ic_sep);
                break;
            case 10:
                holder.imgMes.setImageResource(R.mipmap.ic_oct);
                break;
            case 11:
                holder.imgMes.setImageResource(R.mipmap.ic_nov);
                break;
            case 12:
                holder.imgMes.setImageResource(R.mipmap.ic_dic);
                break;

            default:
                holder.imgMes.setImageResource(R.mipmap.ic_ene);

        }

    }

    @Override
    public int getItemCount() {return list.size();}

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView textNomDoc, textDpto, textDelegacion, textNombre;
        public ImageView imgMes;
        CardView cv;

        public ViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.card_view_nomina);

            textNomDoc = (TextView) itemView.findViewById(R.id.txtNomina);
            imgMes = (ImageView) itemView.findViewById(R.id.imgMesNomina);
        }

        @Override
        public void onClick(View view) {


        }
    }

}