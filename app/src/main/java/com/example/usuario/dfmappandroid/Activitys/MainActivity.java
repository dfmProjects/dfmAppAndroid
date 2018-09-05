package com.example.usuario.dfmappandroid.Activitys;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;
import com.example.usuario.dfmappandroid.R;

public class MainActivity extends BaseActivity {


    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private static String TAG = "MainActivity";
    ImageButton btnNominas, btnNoticias, btnPerfil;
    Button btnRequest2;
    TextView textto;
    RequestQueue requestQueue;

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    String endPoint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //Permisos de la aplicación
        MainActivity.verifyStoragePermissions(this);

        Cache cache = new DiskBasedCache(getCacheDir(),1024*1024);
        Network network = new BasicNetwork(new HurlStack());
        requestQueue = new RequestQueue(cache,network);
        requestQueue.start();

        btnNominas = (ImageButton) findViewById(R.id.btnNominas);
        btnNoticias = (ImageButton) findViewById(R.id.btnNoticias);
        btnPerfil = (ImageButton) findViewById(R.id.btnPerfil);


        btnNominas.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent startActivity = new Intent(getApplicationContext(), NominaActivity.class);
                startActivity(startActivity);
            }
        });


        btnNoticias.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent startActivity = new Intent(getApplicationContext(), NoticiaActivity.class);
                startActivity(startActivity);
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent startActivity = new Intent(getApplicationContext(), PerfilActivity.class);
                startActivity(startActivity);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id)
        {
            case R.id.action_main: return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission

        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity, PERMISSIONS_STORAGE,REQUEST_EXTERNAL_STORAGE );

        }

    }
}
