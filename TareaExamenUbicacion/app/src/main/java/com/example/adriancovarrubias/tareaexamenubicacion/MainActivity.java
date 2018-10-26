package com.example.adriancovarrubias.tareaexamenubicacion;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    private Button btnAgregar;
    private ListView listaVista;
    private TextView txtUbicacion;
    private SharedPreferences sp;
    private SharedPreferences.Editor editor;
    protected Location mLastLocation;
    private LocationRequest mLocationRequest;
    private GoogleApiClient mGoogleApiClient;
    private Geocoder gCoder;
    private List<String> arrayUbicaciones;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUbicacion = (TextView) findViewById(R.id.Ubicacion);
        btnAgregar = (Button) findViewById(R.id.btnAgregar);
        listaVista = (ListView) findViewById(R.id.listView);

        gCoder = new Geocoder(this);
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .enableAutoManage(this, this)
                    .addApi(LocationServices.API)
                    .addConnectionCallbacks(this)
                    .build();
        }

        sp = this.getSharedPreferences("Preferences",0);
        editor = sp.edit();

        arrayUbicaciones = cargarArray("arrayUbicaciones");

        adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arrayUbicaciones);
        listaVista.setAdapter(adapter);
        listaVista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Llamada")
                        .setMessage("¿Esta seguro que quieres llamara esta persona?")
                        .setPositiveButton("Si", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayUbicaciones.remove(i);
                                guardarArray(arrayUbicaciones,"arrayUbicaciones");
                                adapter.notifyDataSetChanged();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();
            }
        });

        listaVista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                arrayUbicaciones.remove(i);
                guardarArray(arrayUbicaciones,"arrayUbicaciones");
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayUbicaciones.add(txtUbicacion.getText().toString());

                guardarArray(arrayUbicaciones,"arrayUbicaciones");
                adapter.notifyDataSetChanged();
            }
        });
    }

    public void guardarArray(List<String> array, String arrayName) {
        editor.putInt(arrayName +"_size", array.size());
        for(int i=0;i<array.size();i++)
            editor.putString(arrayName + "_" + i, array.get(i));
        editor.commit();
    }

    public List<String> cargarArray(String arrayName) {
        int size = sp.getInt(arrayName + "_size", 0);
        List<String> arrayU = new ArrayList<String>();
        for(int i=0;i<size;i++)
            arrayU.add(sp.getString(arrayName + "_" + i, "registro no encontrado"));
        return arrayU;
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (!checarPermisos())
                    checarPermisos();
                else
                    Toast.makeText(this, "Permisos Concedidos", Toast.LENGTH_LONG).show();
            }
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mLocationRequest = LocationRequest.create();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(10); // Update location every second

        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onLocationChanged(Location location) {
        mLastLocation = location;
        Toast.makeText(MainActivity.this,"Cambio la ubicación",Toast.LENGTH_LONG);
        updateUI();
    }

    private void updateUI() {
        List<Address> addresses = null;
        try {
            addresses = gCoder.getFromLocation(mLastLocation.getLatitude(), mLastLocation.getLongitude(), 1);
            if (addresses != null && addresses.size() > 0)
                txtUbicacion.setText(addresses.get(0).getAddressLine(0).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean checarPermisos(){
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},100);
            return false;
        }

        if (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            return true;
        } else {
            permisosFaltantes();
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 100:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    checarPermisos();
                } else {
                    AlertDialog.Builder alert = new AlertDialog.Builder(this);
                    alert.setTitle("Acceso a la ubicación");
                    alert.setMessage("Para poder utilizar la app es necesario activar los permisos de ubicación");
                    alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent i = new Intent();
                            i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package",getPackageName(),null);
                            i.setData(uri);
                            startActivity(i);
                            finish();
                        }
                    });
                    alert.setCancelable(false);
                    alert.show();
                }
                break;
            case 101:
                //error
        }
    }

    private void permisosFaltantes(){
        onBackPressed();
        Toast.makeText(this,"Te faltan permisos para utilizar la app",Toast.LENGTH_LONG).show();
    }
}
