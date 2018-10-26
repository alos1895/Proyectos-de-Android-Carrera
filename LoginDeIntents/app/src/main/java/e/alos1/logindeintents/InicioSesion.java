package e.alos1.logindeintents;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class InicioSesion extends AppCompatActivity {
    String name;
    String passWord;
    ImageView usuario;
    LinearLayout fondo;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);
        name= getIntent().getStringExtra("name");
        passWord= getIntent().getStringExtra("password");
        usuario=(ImageView)findViewById(R.id.imageView);
        fondo =(LinearLayout)findViewById(R.id.layout);
        ((TextView) findViewById(R.id.textName)).setText(name);
        ((TextView) findViewById(R.id.textPassWord)).setText(passWord);
        Vibrator v = (Vibrator) getSystemService(VIBRATOR_SERVICE);



        if (name.equals("Alonso")){
            v.vibrate(500);

            MediaPlayer mp = MediaPlayer.create(this, R.raw.sip);
            mp.start();
            fondo.setBackgroundColor(Color.GREEN);
            usuario.setImageResource(R.drawable.sivalido);
            new AlertDialog.Builder(InicioSesion.this)
                    .setTitle("Dialog Simple")
                    .setMessage("Estas Bien")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    }).show();
        }else
        {
            long[] pattern = {100,500,100,500,100,500};
            v.vibrate(pattern, -1);
            MediaPlayer mp = MediaPlayer.create(this, R.raw.nop);
            mp.start();
            fondo.setBackgroundColor(Color.RED);
            usuario.setImageResource(R.drawable.validono);
            new AlertDialog.Builder(InicioSesion.this)
                    .setTitle("Dialog Simple")
                    .setMessage("Estas Mal")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {

                            dialog.cancel();
                        }
                    }).show();
        }


    }
}
