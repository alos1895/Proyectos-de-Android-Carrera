package ar.com.tutorialesya.proyecto012;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Actividad2 extends AppCompatActivity {
    WebView webView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad2);

        webView1=(WebView)findViewById(R.id.webView);

        Bundle bundle = getIntent().getExtras();
        String dato=bundle.getString("direccion");
        webView1.setWebViewClient(new WebViewClient());
        webView1.loadUrl("http://" + dato);
    }

    public void finalizar(View v) {
        finish();
    }
}
