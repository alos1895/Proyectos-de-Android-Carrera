package com.example.alos1.proyectointegrador;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Registrarse extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
    }
    public void salir(View v) {
        finish();
    }
}
