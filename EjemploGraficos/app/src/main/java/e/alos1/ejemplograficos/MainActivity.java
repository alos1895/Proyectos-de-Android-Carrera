package e.alos1.ejemplograficos;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new EjemploView(this));
    }

    public class EjemploView extends View {
     public EjemploView(Context context){
         super(context);
     }
     protected void onDraw(Canvas canvas){
         //Dibujas
         Paint pincel = new Paint();
         pincel.setColor(Color.BLUE);
         pincel.setStrokeWidth(8);
         pincel.setStyle(Paint.Style.STROKE);
         pincel.setTextSize(50);

         canvas.drawText("Buenas Tardes",500,450,pincel);
         canvas.drawCircle(150,150,100,pincel);
         canvas.drawOval(400,400,280,100,pincel);
         canvas.drawLine(500,500,150,150,pincel);
         canvas.drawRect(280,200,450,80,pincel);
         canvas.drawRect(50,800,400,400,pincel);
         canvas.drawPoint(700,700,pincel);




     }
    }
}
