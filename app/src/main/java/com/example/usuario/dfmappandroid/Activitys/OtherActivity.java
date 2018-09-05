package com.example.usuario.dfmappandroid.Activitys;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.usuario.dfmappandroid.R;

public class OtherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_perfil:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
