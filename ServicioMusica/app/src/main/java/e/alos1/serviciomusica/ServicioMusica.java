package e.alos1.serviciomusica;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.widget.Toast;

public class ServicioMusica extends Service {

    private MediaPlayer reproductor;
    //Arreglo para notificacion
    private NotificationManager nm;
    public static final int ID_NOTIFICATION_CREAR= 1;
    Notification notificacion;
    Vibrator v;

    @Override
    public void onCreate(){
        Toast.makeText(ServicioMusica.this,"Servico Creado",Toast.LENGTH_SHORT).show();
        reproductor = MediaPlayer.create(this,R.raw.perno_1);
        //paranotificacion
        nm =(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        v = (Vibrator) getSystemService(VIBRATOR_SERVICE);

    }

    public void notificacionMusica(int id, int icono, String title,String contenido){
        NotificationCompat.Builder builder = (NotificationCompat.Builder) new
                NotificationCompat.Builder(this).setSmallIcon(icono)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),
                        R.drawable.icono_musica)).setContentTitle(title).setContentText(contenido)
                .setColor(getResources().getColor(R.color.colorPrimary));

        nm.notify(id,builder.build());
    }

    @Override
    public int onStartCommand(Intent initent,int flags,int idArranque){
        Toast.makeText(this,"Servicio arrancado "+idArranque,Toast.LENGTH_SHORT).show();
        //lanzar servicio
        reproductor.start();
        notificacionMusica(1,R.drawable.icono_musica,"SOS Notificacion","Help me ");
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage("3121503293",null,"Help me",null,null);
        long[] pattern = {100,500,100,500,100,500,200,500,200,500,200,500,100,500,100,500,100,500};
        v.vibrate(pattern, -1);

        return START_STICKY;
    }
    @Override
    public void onDestroy(){
        Toast.makeText(this,"Servicio detenido ",Toast.LENGTH_SHORT).show();
        reproductor.stop();
        nm.cancel(ID_NOTIFICATION_CREAR);

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
