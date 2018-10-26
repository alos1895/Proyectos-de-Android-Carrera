package com.example.alos1.controlcheckbox;

import android.support.v4.content.res.ConfigurationHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etUno,etDos;
    private TextView tvResultado;
    private CheckBox cbSuma,cbResta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUno=(EditText)findViewById(R.id.etUno);
        etDos=(EditText)findViewById(R.id.etDos);
        tvResultado=(TextView)findViewById(R.id.tvResultado);
        cbSuma=(CheckBox)findViewById(R.id.cbSuma);
        cbResta=(CheckBox)findViewById(R.id.cbResta);

    }
    public void operar(View view) {
        String valor1=etUno.getText().toString();
        String valor2=etDos.getText().toString();
        int nro1=Integer.parseInt(valor1);
        int nro2=Integer.parseInt(valor2);
        String resu="";
        if (cbSuma.isChecked()==true) {
            int suma=nro1+nro2;
            resu="La suma es: "+ suma;
        }
        if (cbResta.isChecked()==true) {
            int resta=nro1-nro2;
            resu=resu + " La resta es: "+ resta;
        }
        tvResultado.setText(resu);
    }
}
