package com.example.alos1.compartirfacebook;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng colima = new LatLng(19.2462172,-103.7259141);
        mMap.addMarker(new MarkerOptions().position(colima).title("Marker in Colima"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(colima));
        mMap.getMaxZoomLevel();


        /*Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(19.2477473,-103.7172504),
                        new LatLng(19.2611053,-103.726054),
                        new LatLng(19.2654172,-103.7262582)).width(5)
                .color(Color.RED));*/
        /*String LINE = "f_n_EdagaLbScApCtkA";
        List decodedPath = PolyUtil.decode(LINE);
        mMap.addPolyline(new PolylineOptions().addAll(decodedPath));*/


    }
}
