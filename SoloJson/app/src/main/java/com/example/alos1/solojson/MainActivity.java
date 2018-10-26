package com.example.alos1.solojson;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {


    private TextView tv1;


    String url = "http://dejabus.com/cgi-bin/api?op=1&lat=19.25509683&lon=-103.74967980";

    ArrayList<HashMap<String, String>> tiemposList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tiemposList = new ArrayList<>();

        tv1 = (TextView) findViewById(R.id.tv1);

        HttpHandler sh = new HttpHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                JSONArray tiempos = jsonObj.getJSONArray("rutas");

                // looping through All Contacts
                for (int i = 0; i < tiempos.length(); i++) {
                    JSONObject c = tiempos.getJSONObject(i);

                    String direccion = c.getString("direccion");
                    String nombre = c.getString("nombre");
                    String t1 = c.getString("t1");

                    // tmp hash map for single contact
                    HashMap<String, String> tiempo = new HashMap<>();

                    // adding each child node to HashMap key => value
                    tiempo.put("direccion", direccion);
                    tiempo.put("nombre", "LA " + nombre);
                    tiempo.put("t1", "Pasa en " + t1 + " minutos");

                    // adding contact to contact list
                    tiemposList.add(tiempo);
                    tv1.setText(tiempo.toString());
                }
            } catch (final JSONException e) {
                //     Log.e(TAG, "Json parsing error: " + e.getMessage());
                //   runOnUiThread(new Runnable() {
                //     @Override
                //   public void run() {
                //     Toast.makeText(getApplicationContext(),
                //           "Json parsing error: " + e.getMessage(),
                //         Toast.LENGTH_LONG)
                //       .show();
            }
            // });

        }
    }
}



