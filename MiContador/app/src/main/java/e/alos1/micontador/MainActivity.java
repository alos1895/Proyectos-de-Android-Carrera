package e.alos1.micontador;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    int contador=0;
    CheckBox checkBox;
    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView)findViewById(R.id.tvCamba);
        checkBox = (CheckBox)findViewById(R.id.checkBox);
        edit = (EditText)findViewById(R.id.editText2);
        if(contador<0){
            checkBox.setVisibility(View.INVISIBLE);
        }
    }
    public void incrementarCont(View view){
        contador=contador+1;
        textView.setText(""+contador);
    }
    public void decrementarCont(View view){
        if (checkBox.isChecked()){
            if(contador==0){
                textView.setText(""+contador);
            }else{
                contador=contador-1;
                textView.setText(""+contador);
            }
        }else{
        contador=contador-1;
        textView.setText(""+contador);
    }
    }
    public void restablcerCont(View view){
        contador= Integer.parseInt(edit.getText().toString());
        textView.setText(""+contador);
    }

}
