package com.example.usuario.dfmappandroid;

import android.os.Bundle;
import android.view.MenuItem;

public class OtherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_other:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
