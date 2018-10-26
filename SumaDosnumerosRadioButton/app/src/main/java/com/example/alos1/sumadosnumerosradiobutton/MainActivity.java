package com.example.alos1.sumadosnumerosradiobutton;

import android.os.ParcelUuid;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    private EditText etOne,etTwo;
    private TextView tvResultado;
    private RadioButton rbtSuma,rbtResta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        etOne=(EditText)findViewById(R.id.etOne);
        etTwo=(EditText)findViewById(R.id.etTwo);
        tvResultado=(TextView)findViewById((R.id.tvResultado));
        rbtSuma=(RadioButton)findViewById((R.id.rbtSuma));
        rbtResta=(RadioButton)findViewById(R.id.rbtResta);

    }
    public void operar(View view){
        String valor1=etOne.getText().toString();
        String valor2=etTwo.getText().toString();
        int num1=Integer.parseInt(valor1);
        int num2=Integer.parseInt(valor2);
        if (rbtSuma.isChecked()==true){
            int suma=num1+num2;
            String resu=String.valueOf(suma);
            tvResultado.setText(resu);
        }
        if (rbtResta.isChecked()==true){
            int resta=num1-num2;
            String resu=String.valueOf(resta);
            tvResultado.setText(resu);
        }



    }

}
