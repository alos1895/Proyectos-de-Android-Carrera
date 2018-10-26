package com.example.alos1.activityparametros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.et1);

    }
    public void ver (View v) {
        Intent i=new Intent(this,Actividad2.class);
        String dir= "d";
        i.putExtra("direccion", dir.toString());
        startActivity(i);
    }
}
