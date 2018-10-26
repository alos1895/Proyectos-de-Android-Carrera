package e.alos1.graficasestadisticas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* //Creamos  un arreglo para almacenar los datos que graficamos
        ArrayList<BarEntry> entradas = new ArrayList<>();
        //Agregar losdatos a graficas
        entradas.add(new BarEntry(18f,0));
        entradas.add(new BarEntry(20f,1));
        entradas.add(new BarEntry(21f,2));
        entradas.add(new BarEntry(22f,3));
        entradas.add(new BarEntry(23f,4));
        entradas.add(new BarEntry(24f,5));

        //Creamos un objeto dataset tipo barchar
        BarDataSet dataSet = new BarDataSet(entradas,"M3 de agua");

        //creamos un array para almancenar las cadenas de las etiquetas
        ArrayList<String> etiquetas = new ArrayList<String>();
        etiquetas.add("Enero");
        etiquetas.add("Febrero");
        etiquetas.add("Marzo");
        etiquetas.add("Abril");
        etiquetas.add("Mayo");
        etiquetas.add("Junio");

        //Crear objeto grafica de barras
        BarChart grafica = new BarChart(getApplicationContext());

        //Virtualizamos la grafica
        setContentView(grafica);

        //Construif argumentos de la grafica
        BarData datosGrafica = new BarData(etiquetas,dataSet);

        //Asignamos series a la grafica
        grafica.setData(datosGrafica);

        grafica.setDescription("Metros de agua");*/




    }


}
