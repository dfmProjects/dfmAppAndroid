package com.example.usuario.dfmappandroid;

import android.os.Bundle;
import android.view.MenuItem;

public class NoToolbar extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_toolbar);
    }

    @Override
    protected boolean useToolbar() {
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_noToolbar)
            return true;

        return super.onOptionsItemSelected(item);
    }
}
