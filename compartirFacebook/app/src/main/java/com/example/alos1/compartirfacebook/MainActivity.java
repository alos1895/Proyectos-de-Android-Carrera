package com.example.alos1.compartirfacebook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button boton = (Button) findViewById(R.id.button);

    }

    public void compartir(View view) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "https://play.google.com/store/apps/details?id=com.vanillasys.dejabus&hl=es_419");
        startActivity(Intent.createChooser(intent, "Share with"));
    }

    public void maps(View view) {
        //Rutas
        Intent i = new Intent(this, MapsActivity.class);
        startActivity(i);

    }
        public void llamar(View view) {
            Intent i = new Intent(Intent.ACTION_CALL);
            i.setData(Uri.parse("tel:911"));
            startActivity(i);

    }
}
