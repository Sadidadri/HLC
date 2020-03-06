package com.example.practica9galletas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView texto;
    Button botonComer;
    ImageView imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (TextView) findViewById(R.id.textoComida);
        botonComer = (Button) findViewById(R.id.comerGalleta);
        imagen = (ImageView) findViewById(R.id.imagenPeluche);

    }
    public void comerGalleta(View v){
        texto.setText("Estoy lleno");
        imagen.setImageResource(R.drawable.after_cookie);
    }

}
