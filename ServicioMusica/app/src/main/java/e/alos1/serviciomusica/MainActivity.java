package e.alos1.serviciomusica;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button arrancar;
    Button detener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrancar = (Button) findViewById(R.id.btnArrancar);
        detener = (Button) findViewById(R.id.btnDetener);

        arrancar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Arrancar servicio de musica
                startService(new Intent(MainActivity.this,ServicioMusica.class));
            }
        });
        detener.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(MainActivity.this,ServicioMusica.class));
            }
        });
    }
}
