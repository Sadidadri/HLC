package com.example.ejercicio7;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView texto;
    int contador = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Boton 1
        texto = (TextView) findViewById(R.id.texto);

        //Boton 2
        Button boton2 = (Button) findViewById(R.id.boton2);
        boton2.setOnClickListener(new Button.OnClickListener(){
            public void onClick (View v){
                TextView texto = (TextView) findViewById(R.id.texto);
                texto.setText("Has pulsado el boton 2");
            }
        });
        boton2.setOnLongClickListener(new Button.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                texto.setText("Has pulsado mucho rato el boton 2.");
                return true;
            }
        });
    }

    public void botonClick(View view){

        if(contador % 2 == 0) {
            texto.setText("Esto va perita");
        }else{
            texto.setText("HOLA MUNDO");
        }
        contador++;
    }
}
