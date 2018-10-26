package e.alos1.panicofemenino;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.speech.RecognizerIntent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class Menu extends AppCompatActivity {

    EditText etPalabraPanico;
    String clave="help";
    String numeroTelefono;
    String nombre;
    double lat = 0;
    double lon = 0;
    int mensajes=0;

    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        numeroTelefono = getIntent().getStringExtra("numeroTelefono");
        nombre = getIntent().getStringExtra("nombre");
        etPalabraPanico = (EditText)findViewById(R.id.etPalabraPanico);
        SharedPreferences pref=getSharedPreferences("datos", Context.MODE_PRIVATE);
        etPalabraPanico.setText(pref.getString("palabra",""));


        //Codigo para verificar permisos
        if  (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1000);
        }

        //Uso de la clase LocationManager para obtener la localizacion del GPS
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(this);
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,
                (LocationListener) Local);
        Local.getMainActivity();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Extraer palabra de caja de texto e igualarla a la variable clave

        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:

                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.
                                    EXTRA_RESULTS);
                    String strSpeech2Text = speech.get(0);

                    if (strSpeech2Text.equals(clave)){
                        //String mensajef = mensaje1.getText().toString();
                        enviarMensaje();

                    }


                }

                break;
            default:

                break;
        }
        SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString("palabra", etPalabraPanico.getText().toString());
        editor.commit();
    }
    public void onClickImgBtnHablar(View v) {
        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        // Configura el Lenguaje (Español-México)
        //intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNIZE_SPEECH_ACTIVITY);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Tú dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }

    }
    public void enviarMensaje() {
        String date = (DateFormat.format("yyyy-MM-dd HH:mm", new java.util.Date()).toString());
        String mensajeAlerta = "";
        String locMaps="google.com/maps/search/";
        SmsManager sms = SmsManager.getDefault();

        mensajeAlerta="Ayudame soy :" + nombre +"\nFecha : " + date;
        sms.sendTextMessage(numeroTelefono,null,mensajeAlerta,null,null);
        Toast.makeText(getApplicationContext(), mensajeAlerta, Toast.LENGTH_SHORT).show();
        mensajeAlerta= locMaps+lat+","+lon + "\nFecha : " + date;
        sms.sendTextMessage(numeroTelefono,null,mensajeAlerta,null,null);
        Toast.makeText(getApplicationContext(), mensajeAlerta, Toast.LENGTH_SHORT).show();
        esperarTresMinutos();
        if (mensajes==30){
            finish(); }
    }
    public void esperarTresMinutos() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
        enviarMensaje();
        mensajes++;
            }
        }, 60000);
    }
    //---------------------------------------------- Aqui empieza la Clase Localizacion-------------
    public class Localizacion implements LocationListener {
        Menu mainActivity;

        public Menu getMainActivity() {

            return mainActivity;
        }
        public void setMainActivity(Menu mainActivity) {
            this.mainActivity = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion
            loc.getLatitude();
            lat=loc.getLatitude();
            loc.getLongitude();
            lon=loc.getLongitude();
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            //Cuadro para encender el GPS
        }
        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado

        }
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // Este metodo se ejecuta cada vez que se detecta un cambio en el
            // status del proveedor de localizacion (GPS)
            // Los diferentes Status son:
            // OUT_OF_SERVICE -> Si el proveedor esta fuera de servicio
            // TEMPORARILY_UNAVAILABLE -> Temporalmente no disponible pero se
            // espera que este disponible en breve
            // AVAILABLE -> Disponible
        }
    }/* Fin de la clase localizacion */

}