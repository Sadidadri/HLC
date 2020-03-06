package com.example.ejercicio8suma;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int num1;
    int num2;

    TextView resultado;
    EditText valor1;
    EditText valor2;
    Button botonSuma;
    Button botonResta;
    Button botonMultiplicar;
    Button botonDividir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valor1 = (EditText) findViewById(R.id.edit1) ;
        valor2 = (EditText) findViewById(R.id.edit2) ;
        botonSuma = (Button) findViewById(R.id.botonSuma);
        botonResta = (Button) findViewById(R.id.botonResta);
        botonMultiplicar = (Button) findViewById(R.id.botonMultiplica);
        botonDividir = (Button) findViewById(R.id.botonDivide);
        resultado = (TextView) findViewById(R.id.resultado);
    }

    public void botonSumar(View v){
        num1 = Integer.parseInt(valor1.getText().toString());
        num2 = Integer.parseInt(valor2.getText().toString());

        resultado.setText(Integer.toString(num1 + num2));
    }
    public void botonRestar(View v){
        num1 = Integer.parseInt(valor1.getText().toString());
        num2 = Integer.parseInt(valor2.getText().toString());

        resultado.setText(Integer.toString(num1 - num2));
    }

    public void botonMultiplicar(View v){
        num1 = Integer.parseInt(valor1.getText().toString());
        num2 = Integer.parseInt(valor2.getText().toString());

        resultado.setText(Integer.toString(num1 * num2));
    }

    public void botonDividir(View v){
        num1 = Integer.parseInt(valor1.getText().toString());
        num2 = Integer.parseInt(valor2.getText().toString());

        resultado.setText(Float.toString((float)num1 / num2));
    }

}
