package e.alos1.panicofemenino;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {

    EditText etNombre;
    EditText etCelular;
    String nombre;
    String celular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre =(EditText)findViewById(R.id.etNombre);
        etCelular = (EditText)findViewById(R.id.etNumeroCelular);

        SharedPreferences pref=getSharedPreferences("datos", Context.MODE_PRIVATE);
        etCelular.setText(pref.getString("tel",""));
        etNombre.setText(pref.getString("nombre",""));

        //Codigo para verificar permisos de ubicacion
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1000);
        }

    }
    public void siguiente(View v){
        //Guardar Telefono
        SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("nombre", etNombre.getText().toString());
        editor.putString("tel", etCelular.getText().toString());
        editor.commit();

        nombre= etNombre.getText().toString();
        celular= etCelular.getText().toString();

        Intent intent=new Intent(MainActivity.this,Menu.class);
        if(etCelular.getText().toString().trim().length() > 0 &&
                etNombre.getText().toString().trim().length()>0)
        {
            intent.putExtra("numeroTelefono",celular);
            intent.putExtra("nombre",nombre);
            startActivity(intent);
        }else {
            Toast.makeText(getApplicationContext(), "Ingresa los datos", Toast.LENGTH_SHORT).show();
        }
    }
}

