package com.example.practica10uncafe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView nombre;
    CheckBox crema;
    CheckBox chocolate;
    Button botonMenos;
    TextView cantidad;
    Button botonMas;
    Button botonOrder;
    int cantidadNumerica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre =(TextView) findViewById(R.id.nombre);
        crema = (CheckBox) findViewById(R.id.checkCrema);
        chocolate = (CheckBox) findViewById(R.id.chechChocolate);
        botonMenos = (Button) findViewById(R.id.menos);
        cantidad = (TextView) findViewById(R.id.numero);
        botonMas = (Button) findViewById(R.id.mas);
        botonOrder = (Button) findViewById(R.id.order);
    }

    public void restarTopping(View v){
        cantidadNumerica = Integer.parseInt(cantidad.getText().toString());

        if(cantidadNumerica > 0){
            cantidad.setText(Integer.toString(cantidadNumerica - 1));
        }
    }
    public void sumarTopping(View v){
        cantidadNumerica = Integer.parseInt(cantidad.getText().toString());

        if(cantidadNumerica < 100){
            cantidad.setText(Integer.toString(cantidadNumerica + 1));
        }
    }

    public void mostrarToast(View v){
        String cadenaToast = "Nombre: "+nombre.getText().toString()+"\n" +
                "Añadir crema? "+crema.isChecked()+"\n" +
                "Añadir chocolate? "+chocolate.isChecked()+"\n" +
                "Cantidad: "+cantidad.getText().toString()+"\n" +
                "Total: "+calcularPrecio()+"$ \n" +
                "Gracias!";

        Toast resumen =
                Toast.makeText(getApplicationContext(),cadenaToast, Toast.LENGTH_SHORT);

        resumen.show();
    }

    private String calcularPrecio() {
        double precio = 1.10;

        if(crema.isChecked()){
            precio = precio + 0.30;
        }
        if(chocolate.isChecked()){
            precio = precio + 0.50;
        }
        precio = Math.round(precio*Integer.parseInt(cantidad.getText().toString()) * 100) / 100d; //Redondea a 2 decimales.
        return Double.toString(precio);
    }

}
