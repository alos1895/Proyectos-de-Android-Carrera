package e.alos1.logindeintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    EditText txtName;
    EditText txtPassWord;
    String name;
    String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtName = (EditText)findViewById(R.id.txtName);
        txtPassWord =(EditText)findViewById(R.id.txtPassword);
    }
    public void login(View v){
        name= txtName.getText().toString();
        passWord = txtPassWord.getText().toString();

        Intent intent=new Intent(MainActivity.this,InicioSesion.class);
        intent.putExtra("name",name);
        intent.putExtra("password",passWord);
        startActivity(intent);

    }
}
