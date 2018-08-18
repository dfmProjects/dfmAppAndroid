package com.example.usuario.dfmappandroid;

import java.util.*;
import android.app.LauncherActivity;
import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class NominasAdapter extends ArrayAdapter<Nomina>{

    public NominasAdapter(Context context, List<Nomina> objects) {
        super(context, 0, objects);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // ¿Existe el view actual?
        if (null == convertView) {
            convertView = inflater.inflate(
                    R.layout.list_item_nomina,
                    parent,
                    false);
        }

        // Referencias UI.
        ImageView mes = (ImageView) convertView.findViewById(R.id.img_item_list);
        TextView name = (TextView) convertView.findViewById(R.id.tv_name);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView company = (TextView) convertView.findViewById(R.id.tv_company);

        Nomina nomina = (Nomina) getItem(position);

        // Setup.
        Glide.with(getContext()).load(nomina.getmImage()).into(mes);
        name.setText(nomina.getnMes());
        title.setText(nomina.getnMes());
        company.setText(nomina.getnNomina());

        return convertView;
    }

}
