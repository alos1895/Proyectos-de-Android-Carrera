package com.example.fanny.menu;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.item_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();// aqui es donde se guara el valor que trae la variable del menu

        //noinspection SimplifiableIfStatement
        if (id == R.id.agregar) {
            Toast.makeText(this, "Opción agregar Seleccionar", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.buscar){
            Toast.makeText(this, "Opción Buscar ", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.modificar){
            Toast.makeText(this, "Opción modificar ", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.eliminar){
            Toast.makeText(this, "Opcion de Eliminar ", Toast.LENGTH_SHORT).show();
            return true;
        }else if(id == R.id.salir){
            Toast.makeText(this, "Opción salir", Toast.LENGTH_SHORT).show();
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
