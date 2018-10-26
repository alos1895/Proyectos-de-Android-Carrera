package ar.com.tutorialesya.proyecto012;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText et1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et1=(EditText)findViewById(R.id.editText);
    }

    public void ver (View v) {
        String et = "dejabus.com/cgi-bin/api?op=1&lat=19.25509683&lon=-103.74967980";
        Intent i=new Intent(this,Actividad2.class);
        i.putExtra("direccion", et.toString());
        startActivity(i);
    }
}
