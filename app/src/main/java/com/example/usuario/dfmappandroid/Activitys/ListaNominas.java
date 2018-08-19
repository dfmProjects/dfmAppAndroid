package com.example.usuario.dfmappandroid.Activitys;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.usuario.dfmappandroid.Fragments.NominasFragment;
import com.example.usuario.dfmappandroid.R;

public class ListaNominas extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_nominas);

        NominasFragment leadsFragment = (NominasFragment)
                getSupportFragmentManager().findFragmentById(R.id.nominas_container);

        if (leadsFragment == null) {
            leadsFragment = NominasFragment.newInstance();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.nominas_container, leadsFragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.lista_nominas:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
