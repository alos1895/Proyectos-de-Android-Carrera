package e.alos1.herramientas;

import android.Manifest;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;

import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity  implements SensorEventListener {

    Camera camera;
    Camera.Parameters parameters;
    boolean isFlash = false;
    boolean isOn = false;

    ImageView ivLinterna;
    ImageView ivNivel;
    ImageView ivMusica;
    ImageView ivImagen;
    FrameLayout fragmento;
    MediaPlayer mediaPlayer;
    MediaPlayer mp;

    private long last_update = 0, last_movement = 0;
    private float prevX = 0, prevY = 0, prevZ = 0;
    private float curX = 0, curY = 0, curZ = 0;

    boolean estaPresionado = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmento = (FrameLayout) findViewById(R.id.fragmento);
        ivLinterna = (ImageView) findViewById(R.id.ivLinterna);
        ivNivel = (ImageView) findViewById(R.id.ivNivel);
        ivMusica = (ImageView) findViewById(R.id.ivMusica);
        ivImagen = (ImageView) findViewById(R.id.ivImagen);


        ((TextView) findViewById(R.id.txtMovimientoX)).setVisibility(View.INVISIBLE);
        ((TextView) findViewById(R.id.txtMovimientoY)).setVisibility(View.INVISIBLE);
        ((TextView) findViewById(R.id.txtMovimientoZ)).setVisibility(View.INVISIBLE);
         mp = MediaPlayer.create(this, R.raw.sip);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,}, 1000);
        } else {


            if (getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {
                camera = Camera.open();
                parameters = camera.getParameters();
                isFlash = true;
            }


            ivLinterna.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                ivLinterna.setImageResource(R.drawable.lantern_activada);
                                ivImagen.setImageResource(R.drawable.lantern_activada);

                                parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                                camera.setParameters(parameters);
                                camera.startPreview();
                                isOn = true;
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            estaPresionado = false;
                            ivLinterna.setImageResource(R.drawable.lantern);
                            ivImagen.setImageResource(R.drawable.lantern);
                            parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                            camera.setParameters(parameters);
                            camera.stopPreview();
                            isOn = false;

                    }
                    return true;
                }

            });

            ivNivel.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                ivNivel.setImageResource(R.drawable.leve_activada);
                                ivImagen.setImageResource(R.drawable.leve_activada);
                                ((TextView) findViewById(R.id.txtMovimientoX)).setVisibility(View.VISIBLE);
                                ((TextView) findViewById(R.id.txtMovimientoY)).setVisibility(View.VISIBLE);
                                ((TextView) findViewById(R.id.txtMovimientoZ)).setVisibility(View.VISIBLE);
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            estaPresionado = false;
                            ivNivel.setImageResource(R.drawable.level);
                            ivImagen.setImageResource(R.drawable.level);
                            ((TextView) findViewById(R.id.txtMovimientoX)).setVisibility(View.INVISIBLE);
                            ((TextView) findViewById(R.id.txtMovimientoY)).setVisibility(View.INVISIBLE);
                            ((TextView) findViewById(R.id.txtMovimientoZ)).setVisibility(View.INVISIBLE);

                    }
                    return true;
                }

            });


            ivMusica.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {

                        case MotionEvent.ACTION_DOWN:

                            if (!estaPresionado) {
                                estaPresionado = true;
                                ivMusica.setImageResource(R.drawable.music_activada);
                                ivImagen.setImageResource(R.drawable.music_activada);
                                mp.start();
                            }
                            break;
                        case MotionEvent.ACTION_UP:
                            estaPresionado = false;
                            ivMusica.setImageResource(R.drawable.music);
                            ivImagen.setImageResource(R.drawable.music);
                            mp.pause();
                    }
                    return true;
                }

            });
        }
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> sensors = sm.getSensorList(Sensor.TYPE_ACCELEROMETER);
        if (sensors.size() > 0) {
            sm.registerListener(this, sensors.get(0),
                    SensorManager.SENSOR_DELAY_GAME);
        }
    }
    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        SensorManager sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        sm.unregisterListener(this);
    }
    @Override
    public void onSensorChanged(SensorEvent event) {
        synchronized (this) {
            long current_time = event.timestamp;
            curX = event.values[0];
            curY = event.values[1];
            curZ = event.values[2];
            if (prevX == 0 && prevY == 0 && prevZ == 0) {
                last_update = current_time;
                last_movement = current_time;
                prevX = curX;
                prevY = curY;
                prevZ = curZ;
            }
            long time_difference = current_time - last_update;
            if (time_difference > 0) {
                float movement = Math.abs((curX + curY + curZ) - (prevX - prevY - prevZ)) / time_difference;
                float min_movement = 1E-6f;
                if (movement > min_movement) {
                    last_movement = current_time;
                }
                prevX = curX;
                prevY = curY;
                prevZ = curZ;
                last_update = current_time;
            }
            // Log.i(LOGTAG," X:"+curX+", Y:"+curY+", Z:"+curZ);
            ((TextView) findViewById(R.id.txtMovimientoX)).setText("X:"+curX);
            ((TextView) findViewById(R.id.txtMovimientoY)).setText("Y:"+curY);
            ((TextView) findViewById(R.id.txtMovimientoZ)).setText("Z:"+curZ);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}